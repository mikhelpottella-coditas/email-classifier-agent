package com.ai.emailagent.services;

import com.ai.emailagent.tools.DataReader;
import com.ai.emailagent.tools.FileReader;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final ChatClient chatClient;

    private final FileReader fileReader;
    private final DataReader dataReader;

    public AgentService(ChatClient.Builder chatClientBuilder, FileReader fileReader, DataReader dataReader) {
        this.chatClient = chatClientBuilder.build();
        this.dataReader = dataReader;
        this.fileReader = fileReader;
    }

    public String ask(String msg) {

        return chatClient.prompt()
                .system("""
                        you are a email classification expert
                        - Classify the email as one of five categories: refund request, order status, product complaint, account/login issue, or other.
                        - Assign a priority of high, medium, or low. An email about a payment problem, a threat to cancel or leave, or a safety concern must be high.
                        - Look up the customer’s order in the company’s order data. Create this data yourself as a simple file of 10 to 15 fake orders, each with an order ID, customer email, item, amount, status, and order date. Use the order wherever it is relevant to the email.
                        - Draft a polite reply that cites the customer’s actual order details. The reply must not promise a refund or a delivery date that the order data doesn’t support.
                        - Do not draft a reply if the email is vague, abusive, or refers to an order that doesn’t exist, or if the system isn’t confident in its classification. Flag the email for human review with a one-line reason.
                        - Output a structured result for each email that a downstream system can consume. It should contain the category, priority, whether an order was found, the drafted reply or escalation flag, and the reason.
                        """)
                .user(msg)
                .tools()
                .options(ChatOptions.builder()
                        .temperature(0.2)
                        .maxTokens(1000)
                        .topP(0.7)
                )
                .tools(fileReader)
                .tools(dataReader)
                .call()
                .content();
    }

}
