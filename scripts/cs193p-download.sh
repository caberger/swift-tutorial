#!/usr/bin/env bash
# Download the material for NVS_MC_Swift Winter 2022/23
# (c) Dipl.-Ing. Christian Aberger (2022)

OUTPUT_FOLDER=./target/
BASE_URL=https://cs193p.sites.stanford.edu/sites/g/files/sbiybj16636/files/media/file

mkdir -p $OUTPUT_FOLDER

for lecture in {1..14}
do
    curl -o ${OUTPUT_FOLDER}/l${lecture}.pdf $BASE_URL/l${lecture}.pdf
done

