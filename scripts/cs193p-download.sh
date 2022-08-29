#!/usr/bin/env bash
# Download the material for NVS_MC_Swift Winter 2022/23
# (c) Dipl.-Ing. Christian Aberger (2022)

OUTPUT_FOLDER=./target
BASE_URL=https://cs193p.sites.stanford.edu/sites/g/files/sbiybj16636/files/media/file
ALT_URL=https://github.com/weitieda/cs193p-2020-swiftui/raw/master/documents/Slides

mkdir -p $OUTPUT_FOLDER

downloadOrig() {
    curl --location -o ${OUTPUT_FOLDER}/l${2}.pdf $1/l${2}.pdf
}

for lecture in {1..6}
do
    downloadOrig $BASE_URL ${lecture}
done
for lecture in {7..8}
do
    downloadOrig $ALT_URL ${lecture}
done
for lecture in {9..14}
do
    downloadOrig $BASE_URL ${lecture}
done
