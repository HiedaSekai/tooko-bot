package tookox.core.agent

import tookox.core.client.*

class AgentClient(val dir: File) : TdClient(TdOptions().databaseDirectory(dir.path))