DESCRIPTION = "Revised OpenFlow Library IPCTLD (ROFL)"
SECTION = "net"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=e4d7e1de5b6d0c6597a7f5cb61577266"

DEPENDS = "rofl-core"
RDEPENDS = "busybox-runit"

PR = "r1"

SRC_URI = "git://gitolite@codebasin.net/ipctld.git;protocol=ssh;tag=v${PV} \
	   file://run"

S = "${WORKDIR}/git"

inherit autotools

INITSCRIPT_NAME = "ipctld"
INITSCRIPT_PARAMS = "defaults 51"

EXTRA_OECONF += "--enable-debug"
CXXFLAGS_append = " -I${STAGING_INCDIR}/rofl"

do_install_append() {
    install -d ${D}${sysconfdir} \
	${D}${sysconfdir}/rofl \
	${D}/var/service/ipctld

    install -m0755 ${WORKDIR}/run ${D}/var/service/ipctld
}
