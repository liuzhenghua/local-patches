# 简介
修复langchain4j 针对OpenAI 格式的模型，无法传递claude的prompt cache特性。

详情参照：https://platform.claude.com/docs/en/build-with-claude/prompt-caching#structuring-your-prompt

按照claude的官方说法，要在system, tool spec, 最后一条message里加。我目前测试下来（只针对我当前的claude供应商）：
- system 消息加了
- 最后一条user message加了。（claude包括工具结果都是user message，这里也只是为了实现方便弄了个折中）
- tool spec因为我加了没效果，所以这次patch没加