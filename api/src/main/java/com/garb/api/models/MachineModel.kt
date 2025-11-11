package com.garb.api.models

class MachineModel(
    val idMachine: Long = 0,
    val name: String? = null,
    val serialNo: String? = null,
    val location: String? = null,
    val minInventProd: Long = 0,
    val maxRow: Long = 0,
    val maxCol: Long = 0,
    val lockedCell: Any? = null,
    val establishmentCode: String? = null,
    val remoteId: String? = null,
    val operationalStatus: String? = null,
    val machineStatus: String? = null,
    val appStatus: String? = null,
    val status: String? = null,
    val lastStatus: String? = null,
    val enabled: Boolean = false,
    val idCompany: String? = null,
    val plugins: MutableList<PluginModel?>? = null,
    val machineSnapshotProds: MutableList<Any?>? = null,
)