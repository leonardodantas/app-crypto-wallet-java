package com.crypto.wallet.infra.database.mongodb.documents;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document("schedule_log")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleLogDocument {

    @Id
    private String id;
    private String name;
    private LocalDateTime updateAt;

    public ScheduleLogDocument(final String name) {
        this.name = name;
        this.updateAt = LocalDateTime.now();
    }

    public static ScheduleLogDocument from(final String name) {
        return new ScheduleLogDocument(name);
    }

    public ScheduleLogDocument of(final LocalDateTime updateAt) {
        return new ScheduleLogDocument(
                this.id,
                this.name,
                updateAt
        );
    }
}
