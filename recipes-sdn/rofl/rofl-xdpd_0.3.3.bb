DESCRIPTION = "The eXtensible OpenFlow datapath daemon"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=5d425c8f3157dbf212db2ec53d9e5132"

DEPENDS = "rofl-core"

SRCREV="2ad91d591c45a5c910bee32d9c60651d8f25152e"

PE = "1"
PR = "r2"

SRC_URI = "\
    git://codebasin.net/xdpd.git;protocol=git \
    file://xdpd.service \
    file://cli.cfg"

FILES_${PN} += "\
    ${sysconfdir}/xdpd/cli.cfg"

CONFFILES_${PN} += "\
    ${sysconfdir}/xdpd/cli.cfg"

S = "${WORKDIR}/git"

inherit autotools systemd

SYSTEMD_SERVICE_${PN} = "xdpd.service"
SYSTEMD_AUTO_ENABLE ?= "disable"

EXTRA_OECONF += "--enable-gnu-linux \
             ${@base_contains("IMAGE_FEATURES", 'debug-tweaks-rofl', '--enable-debug', '', d)}"

CXXFLAGS_append = " -I${STAGING_INCDIR}/rofl"

EXTRA_AUTORECONF += "-I${S}"

do_fix_modules () {
    rm -rf ${S}/src/xdpd/fwd-modules/bcm ${S}/src/xdpd/fwd-modules/gnu_linux_dpdk ${S}/src/xdpd/fwd-modules/octeon5650
}

do_patch_append() {
    bb.build.exec_func('do_fix_modules', d)
}

do_install_append() {
    install -d ${D}${sysconfdir}/xdpd/ \
               ${D}${systemd_unitdir}/system/
    install -m 0744 ${WORKDIR}/cli.cfg ${D}${sysconfdir}/xdpd/
    install -m 0644 ${WORKDIR}/xdpd.service ${D}${systemd_unitdir}/system/
}
