package com.template.memory.domain

import kotlinx.coroutines.CancellationException

class CancelException(
    override val message: String?,
    val item: MemoryItem
) : CancellationException()