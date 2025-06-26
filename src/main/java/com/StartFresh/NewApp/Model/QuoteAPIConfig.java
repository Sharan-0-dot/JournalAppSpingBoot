package com.StartFresh.NewApp.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor

public class QuoteAPIConfig {
    private String key;
    private String value;
}
