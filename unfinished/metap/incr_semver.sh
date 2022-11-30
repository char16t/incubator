#!/bin/bash

# Usage:
#
# ./incr_semver.sh 1.0.0 release
# ./incr_semver.sh 1.0.0 minor
# ./incr_semver.sh 1.0.0 major

# $1 - semver string
# $2 - level to incr {release,minor,major} - release by default
function incr_semver() {
    IFS='.' read -ra ver <<< "$1"
    [[ "${#ver[@]}" -ne 3 ]] && echo "Invalid semver string" && return 1
    [[ "$#" -eq 1 ]] && level='release' || level=$2

    release=${ver[2]}
    minor=${ver[1]}
    major=${ver[0]}

    case $level in
        release)
            release=$((release+1))
        ;;
        minor)
            release=0
            minor=$((minor+1))
        ;;
        major)
            release=0
            minor=0
            major=$((major+1))
        ;;
        *)
            echo "Invalid level passed"
            return 2
    esac
    echo "$major.$minor.$release"
}

incr_semver "${1}" "${2}"
