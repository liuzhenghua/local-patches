# 简介
修复langchain4j 针对OpenAI 格式的模型，无法传递claude的prompt cache特性。

详情参照：https://platform.claude.com/docs/en/build-with-claude/prompt-caching#structuring-your-prompt

按照claude的官方说法，要在system, tool spec, 最后一条message里加。我目前测试下来（只针对我当前的claude供应商）：
- system 消息加了
- 最后一条user message加了。（claude包括工具结果都是user message，这里也只是为了实现方便弄了个折中）
- tool spec因为我加了没效果，所以这次patch没加


## 使用
```kotlin
val builder = OpenAiStreamingChatModel.builder()
        .baseUrl(modelConfig.endpoint)
        .apiKey(modelConfig.apiKey)
        .customHeaders(headers) // 调用lowcode app 的 chat_completion用此鉴权
        .modelName(modelConfig.model)
        .timeout(Duration.ofSeconds(300))
        .strictTools(true)
        .strictJsonSchema(true)
        .enablePromptCache(modelConfig.enablePromptCache)   // 这里启用prompt cache
    if (modelConfig.alias == "kimi-k2.5") {
        builder.customParameters(mapOf(
            "thinking" to mapOf("type" to "disabled"),  // official API
            "extra_body" to mapOf("thinking" to mapOf("type" to "disabled")),  // 兼容litellm
            "chat_template_kwargs" to mapOf("thinking" to false)  // vLLM or SGLang
        ))
        builder.temperature(0.6)  // 非思考模式 0.6， 思考模式1.0
//        builder.returnThinking(true)
//        builder.sendThinking(true)
    } else if (modelConfig.alias.startsWith("glm-")) {
        builder.customParameters(mapOf(
            "thinking" to mapOf("type" to "disabled"),  // official API
            "extra_body" to mapOf("thinking" to mapOf("type" to "disabled")),  // 兼容litellm
            "chat_template_kwargs" to mapOf("thinking" to false)  // vLLM or SGLang
        ))
        builder.temperature(0.7)  // 非思考模式 0.7， 思考模式1.0
    }
    return builder.build()
```