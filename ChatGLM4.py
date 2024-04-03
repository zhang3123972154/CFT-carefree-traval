import json

from langchain.chains import LLMChain
from langchain.llms.base import LLM
from langchain.prompts import PromptTemplate
from typing import List, Optional
from zhipuai import ZhipuAI
from langchain_core.messages.ai import AIMessage
import os
import yaml


#zhipuai_api_key = "1de528d9f1a2e8f40cfe317c64c398a7.3k9l5YXLIgbBmv6O"
    #"7e630b66a305a0495f7e81b373986c57.r3MD7stgIOketzM2"


def tool_config_from_file(tool_name, directory="Tool/"):
    """search tool yaml and return json format"""
    for filename in os.listdir(directory):
        if filename.endswith('.yaml') and tool_name in filename:
            file_path = os.path.join(directory, filename)
            with open(file_path, encoding='utf-8') as f:
                return yaml.safe_load(f)
    return None


class ChatGLM4(LLM):
    max_token: int = 8192
    do_sample: bool = True
    temperature: float = 0.8
    top_p = 0.8
    tokenizer: object = None
    model: object = None
    history: List = []
    tool_names: List = []
    has_search: bool = False
    client: object = None

    def __init__(self):
        super().__init__()
        self.client = ZhipuAI()

    @property
    def _llm_type(self) -> str:
        return "ChatGLM4"

    def stream(self, prompt: str, history=[],stop=None):
        if history is None:
            history = []
        history.append({"role": "user", "content": prompt})
        response = self.client.chat.completions.create(
            model="glm-4",  # 填写需要调用的模型名称
            messages=history,
            stream=True,
        )
        for chunk in response:
            yield chunk.choices[0].delta.content

    def _tool_history(self, prompt: str):
        ans = []
        tool_prompts = prompt.split(
            "You have access to the following tools:\n\n")[1].split("\n\nUse a json blob")[0].split("\n")

        tool_names = [tool.split(":")[0] for tool in tool_prompts]
        self.tool_names = tool_names
        tools_json = []
        for i, tool in enumerate(tool_names):
            tool_config = tool_config_from_file(tool)
            if tool_config:
                tools_json.append(tool_config)
            else:
                ValueError(
                    f"Tool {tool} config not found! It's description is {tool_prompts[i]}"
                )

        ans.append({
            "role": "system",
            "content": "Answer the following questions as best as you can. You have access to the following tools:",
            "tools": tools_json
        })
        query = f"""{prompt.split("Human: ")[-1].strip()}"""
        return ans, query

    def _extract_observation(self, prompt: str):
        return_json = prompt.split("Observation: ")[-1].split("\nThought:")[0]
        self.history.append({
            "role": "observation",
            "content": return_json
        })
        return

    def _extract_tool(self):
        if len(self.history[-1]["metadata"]) > 0:
            metadata = self.history[-1]["metadata"]
            content = self.history[-1]["content"]
            if "tool_call" in content:
                for tool in self.tool_names:
                    if tool in metadata:
                        input_para = content.split("='")[-1].split("'")[0]
                        action_json = {
                            "action": tool,
                            "action_input": input_para
                        }
                        self.has_search = True
                        return f"""
Action: 
```
{json.dumps(action_json, ensure_ascii=False)}
```"""
        final_answer_json = {
            "action": "Final Answer",
            "action_input": self.history[-1]["content"]
        }
        self.has_search = False
        return f"""
Action: 
```
{json.dumps(final_answer_json, ensure_ascii=False)}
```"""

    def _call(self, prompt: str, history: List = [], stop: Optional[List[str]] = ["<|user|>"]):
        if history is None:
            history = []

        history.append({"role": "user", "content": prompt})
        response = self.client.chat.completions.create(
            model="glm-4",  # 填写需要调用的模型名称
            messages=history,
        )

        result = response.choices[0].message.content
        print(result)
        # AIMessage(content=result)
        return result


#llm = ChatGLM4()

# tools = load_tools(['llm-math'],llm=llm)
# agent = initialize_agent(tools,llm,agent = AgentType.ZERO_SHOT_REACT_DESCRIPTION,verbose = True)
# agent.run("帮我计算25的3.5次方")

#llm._call("武大周边景点推荐")



