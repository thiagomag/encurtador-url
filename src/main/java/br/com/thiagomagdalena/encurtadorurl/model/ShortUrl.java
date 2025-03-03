package br.com.thiagomagdalena.encurtadorurl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@Table("short_url")
public class ShortUrl {

    @Id
    private Long id;
    private String shortCode;
    private String originalUrl;
    private Boolean isActive;
    private LocalDateTime expirationDate;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime deletedTmsp;

    public ShortUrl delete() {
        this.deletedTmsp = LocalDateTime.now();
        this.isActive = Boolean.FALSE;
        return this;
    }
}
