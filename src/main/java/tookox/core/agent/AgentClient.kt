package tookox.core.agent

import tookox.core.client.*

class AgentClient(dir: String) : TdClient(TdOptions().databaseDirectory(dir))