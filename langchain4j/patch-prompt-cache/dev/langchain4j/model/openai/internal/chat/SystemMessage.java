package dev.langchain4j.model.openai.internal.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import dev.langchain4j.internal.JacocoIgnoreCoverageGenerated;

import java.util.List;
import java.util.Objects;

import static dev.langchain4j.model.openai.internal.chat.Role.SYSTEM;

@JsonDeserialize(builder = SystemMessage.Builder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public final class SystemMessage implements Message {

    @JsonProperty
    private final Role role = SYSTEM;
    @JsonProperty
    private final List<Content> content;
    @JsonProperty
    private final String name;

    public SystemMessage(Builder builder) {
        this.content = List.of(Content.builder()
                .type(ContentType.TEXT)
                .text(builder.content)
                .enablePromptCache(builder.enablePromptCache)
                .build());
        this.name = builder.name;
    }

    public Role role() {
        return role;
    }

    public List<Content> content() {
        return content;
    }

    public String name() {
        return name;
    }

    @Override
    @JacocoIgnoreCoverageGenerated
    public boolean equals(Object another) {
        if (this == another) return true;
        return another instanceof SystemMessage
                && equalTo((SystemMessage) another);
    }

    @JacocoIgnoreCoverageGenerated
    private boolean equalTo(SystemMessage another) {
        return Objects.equals(role, another.role)
                && Objects.equals(content, another.content)
                && Objects.equals(name, another.name);
    }

    @Override
    @JacocoIgnoreCoverageGenerated
    public int hashCode() {
        int h = 5381;
        h += (h << 5) + Objects.hashCode(role);
        h += (h << 5) + Objects.hashCode(content);
        h += (h << 5) + Objects.hashCode(name);
        return h;
    }

    @Override
    @JacocoIgnoreCoverageGenerated
    public String toString() {
        return "SystemMessage{"
                + "role=" + role
                + ", content=" + content
                + ", name=" + name
                + "}";
    }

    public static SystemMessage from(String content) {
        return from(content, false);
    }

    public static SystemMessage from(String content, Boolean enablePromptCache) {
        return SystemMessage.builder()
                .content(content)
                .enablePromptCache(enablePromptCache)
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static final class Builder {

        private String content;
        private String name;
        private Boolean enablePromptCache;

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder enablePromptCache(Boolean enablePromptCache) {
            this.enablePromptCache = enablePromptCache;
            return this;
        }

        public SystemMessage build() {
            return new SystemMessage(this);
        }
    }
}
