DESCRIPTION = "Revised Erlang PPP Implementation / Flower Application"
SECTION = "devel"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://src/flow_pppctld_app.erl;beginline=1;endline=1;md5=0f89632c1db6ca13ae19de1d649b7acb"

SRCREV="AUTOINC"
PR = "r0"
PV="0.0+git${SRCPV}"

SRC_URI = "git://git@git.tpip.net/flow_pppctld.git;protocol=ssh"

S = "${WORKDIR}/git"
DEPENDS += "erlang-regine erlang-flower erlang-reppp"

inherit tetrapak

python () {
    erlang_def_package("flow-pppctld", "flow_pppctld-*", "ebin", "src include README test", d)
}