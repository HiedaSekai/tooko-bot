package tookox.core.agent

import tookox.core.client.*
import java.io.File

class AgentClient(val dir: File) : TdClient(TdOptions().databaseDirectory(dir.path))