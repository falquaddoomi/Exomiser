/*
 * The Exomiser - A tool to annotate and prioritize genomic variants
 *
 * Copyright (c) 2016-2017 Queen Mary University of London.
 * Copyright (c) 2012-2016 Charité Universitätsmedizin Berlin and Genome Research Ltd.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.monarchinitiative.exomiser.data.genome.parsers;

import org.junit.Test;
import org.monarchinitiative.exomiser.data.genome.model.Allele;
import org.monarchinitiative.exomiser.data.genome.model.AlleleProperty;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.monarchinitiative.exomiser.data.genome.model.AlleleProperty.*;

/**
 * @author Jules Jacobsen <j.jacobsen@qmul.ac.uk>
 */
public class ExacAlleleParserTest {

    private List<Allele> parseLine(String line) {
        ExacAlleleParser instance = new ExacAlleleParser();
        return instance.parseLine(line);
    }

    @Test
    public void testParseCommentLine() throws Exception {
        String line = "#CHR    POS ID    REF ALT QUAL    FILTER  INFO";
        List<Allele> alleles = parseLine(line);
        assertThat(alleles.isEmpty(), is(true));
    }

    @Test
    public void testParseSnp() throws Exception {
        String line = "1\t1154362\t.\tC\tA\t910.03\tPASS\tAC=1;AC_AFR=1;AC_AMR=0;AC_Adj=1;AC_EAS=0;AC_FIN=0;AC_Het=1;AC_Hom=0;AC_NFE=0;AC_OTH=0;AC_SAS=0;AF=8.249e-06;AN=121226;AN_AFR=9172;AN_AMR=11346;AN_Adj=115274;AN_EAS=8390;AN_FIN=6546;AN_NFE=62754;AN_OTH=862;AN_SAS=16204;BaseQRankSum=-5.200e-01;ClippingRankSum=0.132;DP=1610982;FS=0.000;GQ_MEAN=57.65;GQ_STDDEV=17.24;Het_AFR=1;Het_AMR=0;Het_EAS=0;Het_FIN=0;Het_NFE=0;Het_OTH=0;Het_SAS=0;Hom_AFR=0;Hom_AMR=0;Hom_EAS=0;Hom_FIN=0;Hom_NFE=0;Hom_OTH=0;Hom_SAS=0;InbreedingCoeff=-0.0025;MQ=60.00;MQ0=0;MQRankSum=0.416;NCC=539;QD=15.17;ReadPosRankSum=1.08;VQSLOD=2.53;culprit=MQ;DP_HIST=911|2005|1960|371|10614|26641|14506|2563|470|161|99|93|64|51|35|25|16|8|5|15,0|0|0|0|0|1|0|0|0|0|0|0|0|0|0|0|0|0|0|0;GQ_HIST=39|957|81|150|3323|307|237|126|46|71|126|162|41534|8742|1690|1419|552|148|167|736,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1;DOUBLETON_DIST=.;AC_MALE=0;AC_FEMALE=1;AN_MALE=65854;AN_FEMALE=49420;AC_CONSANGUINEOUS=0;AN_CONSANGUINEOUS=2114;Hom_CONSANGUINEOUS=0;CSQ=A|missense_allele|MODERATE|SDF4|ENSG00000078808|Transcript|ENST00000403997|protein_coding|4/5||ENST00000403997.2:c.404G>T|ENSP00000384207.2:p.Asp136Tyr|404|406|136|D/Y|Gat/Tat||1||-1|SNV|1|HGNC|24188||||ENSP00000384207|||UPI000059CF48|deleterious_low_confidence(0.01)|probably_damaging(0.937)|hmmpanther:PTHR10827|||||||||||||||||||||||TCC|C,A|intron_allele&NMD_transcript_allele|MODIFIER|SDF4|ENSG00000078808|Transcript|ENST00000465727|nonsense_mediated_decay||4/6|ENST00000465727.1:c.578-39G>T||||||||1||-1|SNV|1|HGNC|24188||||ENSP00000435962||G3V1E2_HUMAN|UPI000045610D||||||||||||||||||||||||||TCC|C,A|intron_allele|MODIFIER|SDF4|ENSG00000078808|Transcript|ENST00000360001|protein_coding||4/6|ENST00000360001.6:c.578-55G>T||||||||1||-1|SNV|1|HGNC|24188|YES||CCDS30553.1|ENSP00000353094|CAB45_HUMAN||UPI0000127156||||||||||||||||||||||||||TCC|C,A|non_coding_transcript_exon_allele&non_coding_transcript_allele|MODIFIER|SDF4|ENSG00000078808|Transcript|ENST00000494748|retained_intron|1/3||ENST00000494748.1:n.2370G>T||2370||||||1||-1|SNV|1|HGNC|24188|||||||||||||||||||||||||||||||||TCC|C,A|upstream_gene_allele|MODIFIER|TNFRSF4|ENSG00000186827|Transcript|ENST00000497869|retained_intron|||||||||||1|4844|-1|SNV|1|HGNC|11918|||||||||||||||||||||||||||||||||TCC|C,A|downstream_gene_allele|MODIFIER|SDF4|ENSG00000078808|Transcript|ENST00000545427|protein_coding|||||||||||1|1717|-1|SNV|1|HGNC|24188||||ENSP00000444451|CAB45_HUMAN||UPI00017A7B8A||||||||||||||||||||||||||TCC|C,A|non_coding_transcript_exon_allele&non_coding_transcript_allele|MODIFIER|SDF4|ENSG00000078808|Transcript|ENST00000478938|retained_intron|1/2||ENST00000478938.1:n.1312G>T||1312||||||1||-1|SNV|1|HGNC|24188|||||||||||||||||||||||||||||||||TCC|C,A|intron_allele|MODIFIER|SDF4|ENSG00000078808|Transcript|ENST00000263741|protein_coding||4/6|ENST00000263741.7:c.578-55G>T||||||||1||-1|SNV|1|HGNC|24188|||CCDS12.1|ENSP00000263741|CAB45_HUMAN||UPI000013D454||||||||||||||||||||||||||TCC|C,A|upstream_gene_allele|MODIFIER|TNFRSF4|ENSG00000186827|Transcript|ENST00000379236|protein_coding|||||||||||1|4850|-1|SNV|1|HGNC|11918|YES||CCDS11.1|ENSP00000368538|TNR4_HUMAN||UPI00001370E5||||||||||||||||||||||||||TCC|C;AC_POPMAX=1;AN_POPMAX=9172;POPMAX=AFR;K1_RUN=C:1;K2_RUN=CC:0;K3_RUN=CCA:0;ESP_AF_POPMAX=0;ESP_AF_GLOBAL=0;ESP_AC=0;KG_AF_POPMAX=0;KG_AF_GLOBAL=0;KG_AC=0";

        Allele expected = new Allele(1, 1154362, "C", "A");
        Map<AlleleProperty, Float> values = new EnumMap<>(AlleleProperty.class);
        //EXAC_AFR AC_AFR=1/AN_AFR=9172 *100
        values.put(EXAC_AFR, 0.0109027475f);

        List<Allele> alleles = parseLine(line);

        assertThat(alleles.size(), equalTo(1));
        Allele allele = alleles.get(0);

        System.out.println(allele.toString());
        assertThat(allele, equalTo(expected));
        assertThat(allele.getValues(), equalTo(values));
    }

    @Test
    public void testParseSnpWithRsId() throws Exception {
        String line = "1\t1412742\trs112970587\tC\tT\t3895.89\tPASS\tAC=3;AC_AFR=0;AC_AMR=0;AC_Adj=3;AC_EAS=0;AC_FIN=0;AC_Het=3;AC_Hom=0;AC_NFE=2;AC_OTH=1;AC_SAS=0;AF=2.472e-05;AN=121372;AN_AFR=10116;AN_AMR=11472;AN_Adj=119526;AN_EAS=8622;AN_FIN=6500;AN_NFE=65506;AN_OTH=882;AN_SAS=16428;BaseQRankSum=2.19;ClippingRankSum=-5.720e-01;DB;DP=1730085;FS=4.815;GQ_MEAN=66.33;GQ_STDDEV=22.25;Het_AFR=0;Het_AMR=0;Het_EAS=0;Het_FIN=0;Het_NFE=2;Het_OTH=1;Het_SAS=0;Hom_AFR=0;Hom_AMR=0;Hom_EAS=0;Hom_FIN=0;Hom_NFE=0;Hom_OTH=0;Hom_SAS=0;InbreedingCoeff=-0.0007;MQ=54.69;MQ0=0;MQRankSum=1.16;NCC=56;NEGATIVE_TRAIN_SITE;QD=13.03;ReadPosRankSum=1.50;VQSLOD=-6.538e-01;culprit=FS;DP_HIST=139|751|1411|498|27753|15606|5641|1902|1125|1035|987|896|777|627|425|300|225|150|107|331,0|0|0|0|0|0|0|0|2|0|1|0|0|0|0|0|0|0|0|0;GQ_HIST=11|148|42|56|1502|326|455|233|91|129|196|142|35889|6363|2187|2336|1567|649|843|7521,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|3;DOUBLETON_DIST=.;AC_MALE=1;AC_FEMALE=2;AN_MALE=66812;AN_FEMALE=52714;AC_CONSANGUINEOUS=0;AN_CONSANGUINEOUS=2154;Hom_CONSANGUINEOUS=0;CSQ=T|upstream_gene_allele|MODIFIER|ATAD3B|ENSG00000160072|Transcript|ENST00000472194|retained_intron||||||||||rs112970587|1|664|1|SNV|1|HGNC|24007|||||||||||||||||||||||||||||||||CCG|.,T|intron_allele|MODIFIER|ATAD3B|ENSG00000160072|Transcript|ENST00000308647|protein_coding||2/15|ENST00000308647.7:c.282+12C>T|||||||rs112970587|1||1|SNV|1|HGNC|24007|YES||CCDS30.1|ENSP00000311766|ATD3B_HUMAN|Q9H834_HUMAN|UPI000013E044||||||||||||||||||||||||||CCG|.,T|intron_allele|MODIFIER|ATAD3B|ENSG00000160072|Transcript|ENST00000378741|protein_coding||2/7|ENST00000378741.3:c.-223+12C>T|||||||rs112970587|1||1|SNV|1|HGNC|24007||||ENSP00000368015||A6NFL3_HUMAN|UPI0002064DA1||||||||||||||||||||||||||CCG|.,T|upstream_gene_allele|MODIFIER|ATAD3B|ENSG00000160072|Transcript|ENST00000378736|processed_transcript||||||||||rs112970587|1|1687|1|SNV|1|HGNC|24007|||||||||||||||||||||||||||||||||CCG|.,T|regulatory_region_allele|MODIFIER|||RegulatoryFeature|ENSR00001576253|open_chromatin_region||||||||||rs112970587|1|||SNV|1|||||||||||||||||||||||||||||||||||CCG|.;AC_POPMAX=2;AN_POPMAX=65506;POPMAX=NFE;K1_RUN=C:0;K2_RUN=CG:0;K3_RUN=CGG:0;ESP_AF_POPMAX=0;ESP_AF_GLOBAL=0;ESP_AC=0;KG_AF_POPMAX=0;KG_AF_GLOBAL=0;KG_AC=0";

        List<Allele> alleles = parseLine(line);

        Allele expected = new Allele(1, 1412742, "C", "T");
        expected.setRsId("rs112970587");
        Map<AlleleProperty, Float> values = new EnumMap<>(AlleleProperty.class);
        values.put(EXAC_NFE, 0.0030531555f);
        values.put(EXAC_OTH, 0.11337868f);


        assertThat(alleles.size(), equalTo(1));
        Allele allele = alleles.get(0);

        System.out.println(allele.toString());

        assertThat(allele, equalTo(expected));
        assertThat(allele.getRsId(), equalTo("rs112970587"));
        assertThat(allele.getValues(), equalTo(values));
    }

    @Test
    public void testParseSnpMultiAlleleNoRsId() throws Exception {
        String line = "20\t1110696\t.\tA\tG,T\t13638.19\tPASS\tAC=5,1;AC_AFR=1,0;AC_AMR=0,0;AC_Adj=5,1;AC_EAS=0,1;AC_FIN=0,0;AC_Het=5,1,0;AC_Hom=0,0;AC_NFE=0,0;AC_OTH=0,0;AC_SAS=4,0;AF=4.119e-05,8.238e-06;AN=121382;AN_AFR=10002;AN_AMR=11494;AN_Adj=115088;AN_EAS=8540;AN_FIN=6600;AN_NFE=65004;AN_OTH=840;AN_SAS=12608;BaseQRankSum=-1.600e-01;ClippingRankSum=-5.390e-01;DP=2385292;FS=0.000;GQ_MEAN=76.32;GQ_STDDEV=34.29;Het_AFR=1,0,0;Het_AMR=0,0,0;Het_EAS=0,1,0;Het_FIN=0,0,0;Het_NFE=0,0,0;Het_OTH=0,0,0;Het_SAS=4,0,0;Hom_AFR=0,0;Hom_AMR=0,0;Hom_EAS=0,0;Hom_FIN=0,0;Hom_NFE=0,0;Hom_OTH=0,0;Hom_SAS=0,0;InbreedingCoeff=0.0039;MQ=60.00;MQ0=0;MQRankSum=0.350;NCC=33;QD=13.99;ReadPosRankSum=0.718;VQSLOD=5.44;culprit=MQ;DP_HIST=632|2354|3011|653|19652|9657|6195|2414|1311|893|660|679|797|891|1009|1151|1129|1131|1136|5336,0|0|0|0|1|0|1|0|0|0|0|0|1|0|0|0|0|0|0|2,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1;GQ_HIST=87|747|170|197|3982|570|548|264|68|69|26|35|18777|5831|2590|3581|2858|1306|1737|17248,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|5,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1;DOUBLETON_DIST=.,.;AC_MALE=4,0;AC_FEMALE=1,1;AN_MALE=63076;AN_FEMALE=52012;AC_CONSANGUINEOUS=0,0;AN_CONSANGUINEOUS=1372;Hom_CONSANGUINEOUS=0,0;CSQ=A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375415|protein_coding||2/26|ENST00000375415.1:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000364564|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375415|protein_coding||2/26|ENST00000375415.1:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000364564|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|upstream_gene_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375420|protein_coding||||||||||rs371680369|1|747|1|SNV||HGNC|25540||||ENSP00000364569||B4DTE2_HUMAN|UPI00017A7F47||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|upstream_gene_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375420|protein_coding||||||||||rs371680369|2|747|1|SNV||HGNC|25540||||ENSP00000364569||B4DTE2_HUMAN|UPI00017A7F47||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000361221|protein_coding||3/28|ENST00000361221.3:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540|YES||CCDS182.1|ENSP00000355060|ARGAL_HUMAN||UPI00003664EA||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000361221|protein_coding||3/28|ENST00000361221.3:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540|YES||CCDS182.1|ENSP00000355060|ARGAL_HUMAN||UPI00003664EA||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000434513|protein_coding||3/26|ENST00000434513.1:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540||||ENSP00000394621|ARGAL_HUMAN||UPI0001AE7806||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000434513|protein_coding||3/26|ENST00000434513.1:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540||||ENSP00000394621|ARGAL_HUMAN||UPI0001AE7806||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000452522|protein_coding||3/27|ENST00000452522.1:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000399401|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000452522|protein_coding||3/27|ENST00000452522.1:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000399401|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|regulatory_region_allele|MODIFIER|||RegulatoryFeature|ENSR00001517852|promoter_flanking_region||||||||||rs371680369|1|||SNV||||||||||||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|regulatory_region_allele|MODIFIER|||RegulatoryFeature|ENSR00001517852|promoter_flanking_region||||||||||rs371680369|2|||SNV||||||||||||||||||||||A:0.0002|A:0|||||||||||||CGG|;AC_POPMAX=4,1;AN_POPMAX=12608,8540;POPMAX=SAS,EAS;K1_RUN=G:1,G:1;K2_RUN=GG:0,GG:0;K3_RUN=GGT:0,GGT:0;ESP_AF_POPMAX=0.00022696323867421597,0;ESP_AF_GLOBAL=0.00007688759069424123,0;ESP_AC=1,0;KG_AF_POPMAX=0,0;KG_AF_GLOBAL=0,0;KG_AC=0,0";
        List<Allele> alleles = parseLine(line);

        assertThat(alleles.size(), equalTo(2));

        Map<AlleleProperty, Float> values1 = new EnumMap<>(AlleleProperty.class);
        //1/10002 *100
        values1.put(EXAC_AFR, 0.009998f);
        //4/12608 *100
        values1.put(EXAC_SAS, 0.031725887f);

        Allele allele1 = alleles.get(0);
        System.out.println(allele1.toString());
        assertThat(allele1.getChr(), equalTo(20));
        assertThat(allele1.getPos(), equalTo(1110696));
        assertThat(allele1.getRsId(), equalTo("."));
        assertThat(allele1.getRef(), equalTo("A"));
        assertThat(allele1.getAlt(), equalTo("G"));
        assertThat(allele1.getValues(), equalTo(values1));


        Map<AlleleProperty, Float> values2 = new EnumMap<>(AlleleProperty.class);
        //1/8540 *100
        values2.put(EXAC_EAS, 0.011709602f);

        Allele allele2 = alleles.get(1);
        System.out.println(allele2.toString());
        assertThat(allele2.getChr(), equalTo(20));
        assertThat(allele2.getPos(), equalTo(1110696));
        assertThat(allele2.getRsId(), equalTo("."));
        assertThat(allele2.getRef(), equalTo("A"));
        assertThat(allele2.getAlt(), equalTo("T"));
        assertThat(allele2.getValues(), equalTo(values2));
    }

    @Test
    public void testParseSnpMultiAlleleSingleRsId() throws Exception {
        String line = "20\t1110696\trs6040355\tA\tG,T\t13638.19\tPASS\tAC=5,1;AC_AFR=1,0;AC_AMR=0,0;AC_Adj=5,1;AC_EAS=0,1;AC_FIN=0,0;AC_Het=5,1,0;AC_Hom=0,0;AC_NFE=0,0;AC_OTH=0,0;AC_SAS=4,0;AF=4.119e-05,8.238e-06;AN=121382;AN_AFR=10002;AN_AMR=11494;AN_Adj=115088;AN_EAS=8540;AN_FIN=6600;AN_NFE=65004;AN_OTH=840;AN_SAS=12608;BaseQRankSum=-1.600e-01;ClippingRankSum=-5.390e-01;DP=2385292;FS=0.000;GQ_MEAN=76.32;GQ_STDDEV=34.29;Het_AFR=1,0,0;Het_AMR=0,0,0;Het_EAS=0,1,0;Het_FIN=0,0,0;Het_NFE=0,0,0;Het_OTH=0,0,0;Het_SAS=4,0,0;Hom_AFR=0,0;Hom_AMR=0,0;Hom_EAS=0,0;Hom_FIN=0,0;Hom_NFE=0,0;Hom_OTH=0,0;Hom_SAS=0,0;InbreedingCoeff=0.0039;MQ=60.00;MQ0=0;MQRankSum=0.350;NCC=33;QD=13.99;ReadPosRankSum=0.718;VQSLOD=5.44;culprit=MQ;DP_HIST=632|2354|3011|653|19652|9657|6195|2414|1311|893|660|679|797|891|1009|1151|1129|1131|1136|5336,0|0|0|0|1|0|1|0|0|0|0|0|1|0|0|0|0|0|0|2,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1;GQ_HIST=87|747|170|197|3982|570|548|264|68|69|26|35|18777|5831|2590|3581|2858|1306|1737|17248,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|5,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1;DOUBLETON_DIST=.,.;AC_MALE=4,0;AC_FEMALE=1,1;AN_MALE=63076;AN_FEMALE=52012;AC_CONSANGUINEOUS=0,0;AN_CONSANGUINEOUS=1372;Hom_CONSANGUINEOUS=0,0;CSQ=A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375415|protein_coding||2/26|ENST00000375415.1:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000364564|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375415|protein_coding||2/26|ENST00000375415.1:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000364564|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|upstream_gene_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375420|protein_coding||||||||||rs371680369|1|747|1|SNV||HGNC|25540||||ENSP00000364569||B4DTE2_HUMAN|UPI00017A7F47||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|upstream_gene_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375420|protein_coding||||||||||rs371680369|2|747|1|SNV||HGNC|25540||||ENSP00000364569||B4DTE2_HUMAN|UPI00017A7F47||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000361221|protein_coding||3/28|ENST00000361221.3:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540|YES||CCDS182.1|ENSP00000355060|ARGAL_HUMAN||UPI00003664EA||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000361221|protein_coding||3/28|ENST00000361221.3:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540|YES||CCDS182.1|ENSP00000355060|ARGAL_HUMAN||UPI00003664EA||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000434513|protein_coding||3/26|ENST00000434513.1:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540||||ENSP00000394621|ARGAL_HUMAN||UPI0001AE7806||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000434513|protein_coding||3/26|ENST00000434513.1:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540||||ENSP00000394621|ARGAL_HUMAN||UPI0001AE7806||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000452522|protein_coding||3/27|ENST00000452522.1:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000399401|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000452522|protein_coding||3/27|ENST00000452522.1:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000399401|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|regulatory_region_allele|MODIFIER|||RegulatoryFeature|ENSR00001517852|promoter_flanking_region||||||||||rs371680369|1|||SNV||||||||||||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|regulatory_region_allele|MODIFIER|||RegulatoryFeature|ENSR00001517852|promoter_flanking_region||||||||||rs371680369|2|||SNV||||||||||||||||||||||A:0.0002|A:0|||||||||||||CGG|;AC_POPMAX=4,1;AN_POPMAX=12608,8540;POPMAX=SAS,EAS;K1_RUN=G:1,G:1;K2_RUN=GG:0,GG:0;K3_RUN=GGT:0,GGT:0;ESP_AF_POPMAX=0.00022696323867421597,0;ESP_AF_GLOBAL=0.00007688759069424123,0;ESP_AC=1,0;KG_AF_POPMAX=0,0;KG_AF_GLOBAL=0,0;KG_AC=0,0";
        List<Allele> alleles = parseLine(line);

        assertThat(alleles.size(), equalTo(2));

        Map<AlleleProperty, Float> values1 = new EnumMap<>(AlleleProperty.class);
        //1/10002 *100
        values1.put(EXAC_AFR, 0.009998f);
        //4/12608 *100
        values1.put(EXAC_SAS, 0.031725887f);

        Allele allele1 = alleles.get(0);
        System.out.println(allele1.toString());
        assertThat(allele1.getChr(), equalTo(20));
        assertThat(allele1.getPos(), equalTo(1110696));
        assertThat(allele1.getRsId(), equalTo("rs6040355"));
        assertThat(allele1.getRef(), equalTo("A"));
        assertThat(allele1.getAlt(), equalTo("G"));
        assertThat(allele1.getValues(), equalTo(values1));

        Map<AlleleProperty, Float> values2 = new EnumMap<>(AlleleProperty.class);
        //1/8540 *100
        values2.put(EXAC_EAS, 0.011709602f);

        Allele allele2 = alleles.get(1);
        System.out.println(allele2.toString());
        assertThat(allele2.getChr(), equalTo(20));
        assertThat(allele2.getPos(), equalTo(1110696));
        assertThat(allele2.getRsId(), equalTo("rs6040355"));
        assertThat(allele2.getRef(), equalTo("A"));
        assertThat(allele2.getAlt(), equalTo("T"));
        assertThat(allele2.getValues(), equalTo(values2));
    }

    @Test
    public void testParseSnpMultiAlleleMultiRsId() throws Exception {
        String line = "1\t17914164\trs200118651;rs202059104\tG\tA,T\t13638.19\tPASS\tAC=5,1;AC_AFR=1,0;AC_AMR=0,0;AC_Adj=5,1;AC_EAS=0,1;AC_FIN=0,0;AC_Het=5,1,0;AC_Hom=0,0;AC_NFE=0,0;AC_OTH=0,0;AC_SAS=4,0;AF=4.119e-05,8.238e-06;AN=121382;AN_AFR=10002;AN_AMR=11494;AN_Adj=115088;AN_EAS=8540;AN_FIN=6600;AN_NFE=65004;AN_OTH=840;AN_SAS=12608;BaseQRankSum=-1.600e-01;ClippingRankSum=-5.390e-01;DP=2385292;FS=0.000;GQ_MEAN=76.32;GQ_STDDEV=34.29;Het_AFR=1,0,0;Het_AMR=0,0,0;Het_EAS=0,1,0;Het_FIN=0,0,0;Het_NFE=0,0,0;Het_OTH=0,0,0;Het_SAS=4,0,0;Hom_AFR=0,0;Hom_AMR=0,0;Hom_EAS=0,0;Hom_FIN=0,0;Hom_NFE=0,0;Hom_OTH=0,0;Hom_SAS=0,0;InbreedingCoeff=0.0039;MQ=60.00;MQ0=0;MQRankSum=0.350;NCC=33;QD=13.99;ReadPosRankSum=0.718;VQSLOD=5.44;culprit=MQ;DP_HIST=632|2354|3011|653|19652|9657|6195|2414|1311|893|660|679|797|891|1009|1151|1129|1131|1136|5336,0|0|0|0|1|0|1|0|0|0|0|0|1|0|0|0|0|0|0|2,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1;GQ_HIST=87|747|170|197|3982|570|548|264|68|69|26|35|18777|5831|2590|3581|2858|1306|1737|17248,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|5,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1;DOUBLETON_DIST=.,.;AC_MALE=4,0;AC_FEMALE=1,1;AN_MALE=63076;AN_FEMALE=52012;AC_CONSANGUINEOUS=0,0;AN_CONSANGUINEOUS=1372;Hom_CONSANGUINEOUS=0,0;CSQ=A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375415|protein_coding||2/26|ENST00000375415.1:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000364564|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375415|protein_coding||2/26|ENST00000375415.1:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000364564|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|upstream_gene_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375420|protein_coding||||||||||rs371680369|1|747|1|SNV||HGNC|25540||||ENSP00000364569||B4DTE2_HUMAN|UPI00017A7F47||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|upstream_gene_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000375420|protein_coding||||||||||rs371680369|2|747|1|SNV||HGNC|25540||||ENSP00000364569||B4DTE2_HUMAN|UPI00017A7F47||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000361221|protein_coding||3/28|ENST00000361221.3:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540|YES||CCDS182.1|ENSP00000355060|ARGAL_HUMAN||UPI00003664EA||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000361221|protein_coding||3/28|ENST00000361221.3:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540|YES||CCDS182.1|ENSP00000355060|ARGAL_HUMAN||UPI00003664EA||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000434513|protein_coding||3/26|ENST00000434513.1:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540||||ENSP00000394621|ARGAL_HUMAN||UPI0001AE7806||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000434513|protein_coding||3/26|ENST00000434513.1:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540||||ENSP00000394621|ARGAL_HUMAN||UPI0001AE7806||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000452522|protein_coding||3/27|ENST00000452522.1:c.223+24G>A|||||||rs371680369|1||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000399401|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|intron_allele|MODIFIER|ARHGEF10L|ENSG00000074964|Transcript|ENST00000452522|protein_coding||3/27|ENST00000452522.1:c.223+24G>T|||||||rs371680369|2||1|SNV||HGNC|25540|||CCDS30617.1|ENSP00000399401|ARGAL_HUMAN||UPI0000458970||||||||||||A:0.0002|A:0|||||||||||||CGG|,A|regulatory_region_allele|MODIFIER|||RegulatoryFeature|ENSR00001517852|promoter_flanking_region||||||||||rs371680369|1|||SNV||||||||||||||||||||||A:0.0002|A:0|||||||||||||CGG|,T|regulatory_region_allele|MODIFIER|||RegulatoryFeature|ENSR00001517852|promoter_flanking_region||||||||||rs371680369|2|||SNV||||||||||||||||||||||A:0.0002|A:0|||||||||||||CGG|;AC_POPMAX=4,1;AN_POPMAX=12608,8540;POPMAX=SAS,EAS;K1_RUN=G:1,G:1;K2_RUN=GG:0,GG:0;K3_RUN=GGT:0,GGT:0;ESP_AF_POPMAX=0.00022696323867421597,0;ESP_AF_GLOBAL=0.00007688759069424123,0;ESP_AC=1,0;KG_AF_POPMAX=0,0;KG_AF_GLOBAL=0,0;KG_AC=0,0";
        List<Allele> alleles = parseLine(line);

        assertThat(alleles.size(), equalTo(2));

        Allele allele1 = alleles.get(0);
        System.out.println(allele1.toString());
        assertThat(allele1.getChr(), equalTo(1));
        assertThat(allele1.getPos(), equalTo(17914164));
        assertThat(allele1.getRsId(), equalTo("rs200118651"));
        assertThat(allele1.getRef(), equalTo("G"));
        assertThat(allele1.getAlt(), equalTo("A"));
        Map<AlleleProperty, Float> expected1 = new EnumMap<>(AlleleProperty.class);
        //EXAC_AFR 1/10002 *100
        expected1.put(EXAC_AFR, 0.009998f);
        //EXAC_SAS 4/12608 *100
        expected1.put(EXAC_SAS, 0.031725887f);

        assertThat(allele1.getValues(), equalTo(expected1));

        Allele allele2 = alleles.get(1);
        System.out.println(allele2.toString());
        assertThat(allele2.getChr(), equalTo(1));
        assertThat(allele2.getPos(), equalTo(17914164));
        assertThat(allele2.getRsId(), equalTo("rs200118651"));
        assertThat(allele2.getRef(), equalTo("G"));
        assertThat(allele2.getAlt(), equalTo("T"));

        Map<AlleleProperty, Float> expected2 = new EnumMap<>(AlleleProperty.class);
        //EXAC_EAS 1/8540 *100
        expected2.put(EXAC_EAS, 0.011709602f);

        assertThat(allele2.getValues(), equalTo(expected2));
    }

    @Test
    public void testParseSnpMultiAlleleMultiRsIdNotMatch() throws Exception {
        String line = "2\t66667199\trs144159761;rs145647821\tGAA\tG,GCTAA,GCAA,AAA\t4966.01\tVQSRTrancheINDEL97.00to99.00\tAC=8,17,8,1;AC_AFR=0,0,0,0;AC_AMR=0,0,0,0;AC_Adj=0,9,3,0;AC_EAS=0,0,0,0;AC_FIN=0,0,0,0;AC_Het=0,9,3,0,0,0,0,0,0,0;AC_Hom=0,0,0,0;AC_NFE=0,9,1,0;AC_OTH=0,0,2,0;AC_SAS=0,0,0,0;AF=1.352e-04,2.874e-04,1.352e-04,1.691e-05;AN=59154;AN_AFR=252;AN_AMR=112;AN_Adj=3272;AN_EAS=162;AN_FIN=270;AN_NFE=1710;AN_OTH=46;AN_SAS=720;BaseQRankSum=-5.890e-01;ClippingRankSum=0.543;DB;DP=228354;FS=7.605;GQ_MEAN=6.64;GQ_STDDEV=10.25;Het_AFR=0,0,0,0,0,0,0,0,0,0;Het_AMR=0,0,0,0,0,0,0,0,0,0;Het_EAS=0,0,0,0,0,0,0,0,0,0;Het_FIN=0,0,0,0,0,0,0,0,0,0;Het_NFE=0,9,1,0,0,0,0,0,0,0;Het_OTH=0,0,2,0,0,0,0,0,0,0;Het_SAS=0,0,0,0,0,0,0,0,0,0;Hom_AFR=0,0,0,0;Hom_AMR=0,0,0,0;Hom_EAS=0,0,0,0;Hom_FIN=0,0,0,0;Hom_NFE=0,0,0,0;Hom_OTH=0,0,0,0;Hom_SAS=0,0,0,0;InbreedingCoeff=0.3407;MQ=52.86;MQ0=0;MQRankSum=-7.200e-01;NCC=46967;QD=17.93;ReadPosRankSum=0.648;VQSLOD=0.680;culprit=FS;DP_HIST=14214|9443|3233|1569|578|249|131|61|45|28|11|7|3|1|1|0|1|1|0|1,3|2|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0,2|5|6|1|2|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0,4|1|1|1|1|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0,0|1|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0;GQ_HIST=11690|13612|1335|930|1154|278|227|109|44|40|22|12|55|20|5|10|2|4|4|24,0|2|0|1|0|0|1|0|0|0|0|0|0|0|0|0|0|0|0|1,0|1|0|0|0|0|1|0|0|0|0|1|0|0|0|1|0|0|0|12,1|1|0|0|0|0|0|0|0|0|0|1|0|0|0|0|0|0|0|5,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1;DOUBLETON_DIST=.,.,.,.;AC_MALE=.,3,1,.;AC_FEMALE=.,6,2,.;AN_MALE=1626;AN_FEMALE=1646;AC_CONSANGUINEOUS=.,0,0,.;AN_CONSANGUINEOUS=148;Hom_CONSANGUINEOUS=.,0,0,.;CSQ=-|intron_variant&non_coding_transcript_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000490726|processed_transcript||3/4|ENST00000490726.1:n.396+84_396+85delAA||||||||1||1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,-|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000560281|protein_coding||3/9|ENST00000560281.2:c.381+84_381+85delAA||||||||1||1|deletion|1|HGNC|7000||||ENSP00000454209||Q53RD5_HUMAN&Q53R57_HUMAN&H0YNY8_HUMAN|UPI00022F8457||||||||||||||||||||||||||CGAAC|,-|intron_variant&non_coding_transcript_variant|MODIFIER|MEIS1-AS2|ENSG00000230749|Transcript|ENST00000439433|antisense||1/1|ENST00000439433.1:n.88+156_88+157delTT||||||||1||-1|deletion|1|HGNC|40370|YES||||||||||||||||||||||||||||||||CGAAC|,C|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000606455|protein_coding|||||||||||3|2897|1|deletion|1|HGNC|7000||||ENSP00000475982||U3KQK9_HUMAN&Q53R57_HUMAN|UPI00038BB067||||||||||||||||||||||||||CGAAC|,CT|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000488550|protein_coding||3/10|ENST00000488550.1:c.381+83_381+84insCT||||||||2||1|deletion|1|HGNC|7000||||ENSP00000475161||U3KPR8_HUMAN&Q53RD5_HUMAN&Q53R57_HUMAN|UPI00002085A0||||||||||||||||||||||||||CGAAC|,C|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000466811|nonsense_mediated_decay|||||||||||3|639|1|deletion|1|HGNC|7000||||ENSP00000476169|||UPI00038BB077||||||||||||||||||||||||||CGAAC|,A|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000409622|nonsense_mediated_decay|||||||||||4|30|1|deletion|1|HGNC|7000||||ENSP00000386514|||UPI00038BB0A2||||||||||||||||||||||||||CGAAC|,A|intron_variant&non_coding_transcript_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000490726|processed_transcript||3/4|ENST00000490726.1:n.396+83G>A||||||||4||1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,CT|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000466811|nonsense_mediated_decay|||||||||||2|639|1|deletion|1|HGNC|7000||||ENSP00000476169|||UPI00038BB077||||||||||||||||||||||||||CGAAC|,-|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000495021|protein_coding||2/11|ENST00000495021.2:c.186+84_186+85delAA||||||||1||1|deletion|1|HGNC|7000||||ENSP00000440571||Q8IZZ2_HUMAN&Q53R57_HUMAN&F5GYS8_HUMAN|UPI0002065530||||||||||||||||||||||||||CGAAC|,-|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000498705|retained_intron|||||||||||1|2706|1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,C|intron_variant&NMD_transcript_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000491706|nonsense_mediated_decay||2/4|ENST00000491706.1:c.240-495_240-494insC||||||||3||1|deletion|1|HGNC|7000||||ENSP00000475875||U3KQH3_HUMAN|UPI00038BAF5F||||||||||||||||||||||||||CGAAC|,A|intron_variant&NMD_transcript_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000491706|nonsense_mediated_decay||2/4|ENST00000491706.1:c.240-495G>A||||||||4||1|deletion|1|HGNC|7000||||ENSP00000475875||U3KQH3_HUMAN|UPI00038BAF5F||||||||||||||||||||||||||CGAAC|,CT|intron_variant&non_coding_transcript_variant|MODIFIER|MEIS1-AS2|ENSG00000230749|Transcript|ENST00000439433|antisense||1/1|ENST00000439433.1:n.88+157_88+158insAG||||||||2||-1|deletion|1|HGNC|40370|YES||||||||||||||||||||||||||||||||CGAAC|,A|downstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000496248|processed_transcript|||||||||||4|100|1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,-|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000407092|protein_coding||3/11|ENST00000407092.2:c.381+84_381+85delAA||||||||1||1|deletion|1|HGNC|7000||||ENSP00000384461||Q53RD5_HUMAN&Q53R57_HUMAN&F8W8U3_HUMAN|UPI00002085A6||||||||||||||||||||||||||CGAAC|,CT|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000409622|nonsense_mediated_decay|||||||||||2|29|1|deletion|1|HGNC|7000||||ENSP00000386514|||UPI00038BB0A2||||||||||||||||||||||||||CGAAC|,A|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000466811|nonsense_mediated_decay|||||||||||4|640|1|deletion|1|HGNC|7000||||ENSP00000476169|||UPI00038BB077||||||||||||||||||||||||||CGAAC|,CT|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000560281|protein_coding||3/9|ENST00000560281.2:c.381+83_381+84insCT||||||||2||1|deletion|1|HGNC|7000||||ENSP00000454209||Q53RD5_HUMAN&Q53R57_HUMAN&H0YNY8_HUMAN|UPI00022F8457||||||||||||||||||||||||||CGAAC|,-|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000488550|protein_coding||3/10|ENST00000488550.1:c.381+84_381+85delAA||||||||1||1|deletion|1|HGNC|7000||||ENSP00000475161||U3KPR8_HUMAN&Q53RD5_HUMAN&Q53R57_HUMAN|UPI00002085A0||||||||||||||||||||||||||CGAAC|,CT|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000398506|protein_coding||2/10|ENST00000398506.2:c.375+83_375+84insCT||||||||2||1|deletion|1|HGNC|7000||||ENSP00000381518|MEIS1_HUMAN|Q53R57_HUMAN|UPI00005BDA98||||||||||||||||||||||||||CGAAC|,C|intron_variant&non_coding_transcript_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000490726|processed_transcript||3/4|ENST00000490726.1:n.396+83_396+84insC||||||||3||1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,CT|downstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000496248|processed_transcript|||||||||||2|100|1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,CT|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000272369|protein_coding||3/12|ENST00000272369.9:c.381+83_381+84insCT||||||||2||1|deletion|1|HGNC|7000|YES||CCDS46309.1|ENSP00000272369|MEIS1_HUMAN|Q8IZZ2_HUMAN&Q53RD5_HUMAN&Q53R57_HUMAN|UPI000000DA5A||||||||||||||||||||||||||CGAAC|,-|downstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000496248|processed_transcript|||||||||||1|101|1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,C|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000488550|protein_coding||3/10|ENST00000488550.1:c.381+83_381+84insC||||||||3||1|deletion|1|HGNC|7000||||ENSP00000475161||U3KPR8_HUMAN&Q53RD5_HUMAN&Q53R57_HUMAN|UPI00002085A0||||||||||||||||||||||||||CGAAC|,A|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000444274|protein_coding||2/9|ENST00000444274.2:c.285+83G>A||||||||4||1|deletion|1|HGNC|7000||||ENSP00000403206||B7Z4T5_HUMAN|UPI0001914F9B||||||||||||||||||||||||||CGAAC|,A|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000398506|protein_coding||2/10|ENST00000398506.2:c.375+83G>A||||||||4||1|deletion|1|HGNC|7000||||ENSP00000381518|MEIS1_HUMAN|Q53R57_HUMAN|UPI00005BDA98||||||||||||||||||||||||||CGAAC|,CT|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000498705|retained_intron|||||||||||2|2707|1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,A|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000488550|protein_coding||3/10|ENST00000488550.1:c.381+83G>A||||||||4||1|deletion|1|HGNC|7000||||ENSP00000475161||U3KPR8_HUMAN&Q53RD5_HUMAN&Q53R57_HUMAN|UPI00002085A0||||||||||||||||||||||||||CGAAC|,C|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000407092|protein_coding||3/11|ENST00000407092.2:c.381+83_381+84insC||||||||3||1|deletion|1|HGNC|7000||||ENSP00000384461||Q53RD5_HUMAN&Q53R57_HUMAN&F8W8U3_HUMAN|UPI00002085A6||||||||||||||||||||||||||CGAAC|,A|intron_variant&non_coding_transcript_variant|MODIFIER|AC092669.1|ENSG00000244522|Transcript|ENST00000454595|antisense||1/1|ENST00000454595.1:n.340-378C>T||||||||4||-1|deletion|1|Clone_based_vega_gene||YES||||||||||||||||||||||||||||||||CGAAC|,C|downstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000496248|processed_transcript|||||||||||3|100|1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,A|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000560281|protein_coding||3/9|ENST00000560281.2:c.381+83G>A||||||||4||1|deletion|1|HGNC|7000||||ENSP00000454209||Q53RD5_HUMAN&Q53R57_HUMAN&H0YNY8_HUMAN|UPI00022F8457||||||||||||||||||||||||||CGAAC|,CT|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000407092|protein_coding||3/11|ENST00000407092.2:c.381+83_381+84insCT||||||||2||1|deletion|1|HGNC|7000||||ENSP00000384461||Q53RD5_HUMAN&Q53R57_HUMAN&F8W8U3_HUMAN|UPI00002085A6||||||||||||||||||||||||||CGAAC|,C|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000498705|retained_intron|||||||||||3|2707|1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,C|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000495021|protein_coding||2/11|ENST00000495021.2:c.186+83_186+84insC||||||||3||1|deletion|1|HGNC|7000||||ENSP00000440571||Q8IZZ2_HUMAN&Q53R57_HUMAN&F5GYS8_HUMAN|UPI0002065530||||||||||||||||||||||||||CGAAC|,A|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000437869|protein_coding|||||||||||4|611|1|deletion|1|HGNC|7000||||ENSP00000397418|||UPI00038BAF56||||||||||||||||||||||||||CGAAC|,CT|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000444274|protein_coding||2/9|ENST00000444274.2:c.285+83_285+84insCT||||||||2||1|deletion|1|HGNC|7000||||ENSP00000403206||B7Z4T5_HUMAN|UPI0001914F9B||||||||||||||||||||||||||CGAAC|,-|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000444274|protein_coding||2/9|ENST00000444274.2:c.285+84_285+85delAA||||||||1||1|deletion|1|HGNC|7000||||ENSP00000403206||B7Z4T5_HUMAN|UPI0001914F9B||||||||||||||||||||||||||CGAAC|,-|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000606455|protein_coding|||||||||||1|2896|1|deletion|1|HGNC|7000||||ENSP00000475982||U3KQK9_HUMAN&Q53R57_HUMAN|UPI00038BB067||||||||||||||||||||||||||CGAAC|,-|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000466811|nonsense_mediated_decay|||||||||||1|638|1|deletion|1|HGNC|7000||||ENSP00000476169|||UPI00038BB077||||||||||||||||||||||||||CGAAC|,C|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000437869|protein_coding|||||||||||3|610|1|deletion|1|HGNC|7000||||ENSP00000397418|||UPI00038BAF56||||||||||||||||||||||||||CGAAC|,-|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000437869|protein_coding|||||||||||1|609|1|deletion|1|HGNC|7000||||ENSP00000397418|||UPI00038BAF56||||||||||||||||||||||||||CGAAC|,-|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000409622|nonsense_mediated_decay|||||||||||1|28|1|deletion|1|HGNC|7000||||ENSP00000386514|||UPI00038BB0A2||||||||||||||||||||||||||CGAAC|,C|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000444274|protein_coding||2/9|ENST00000444274.2:c.285+83_285+84insC||||||||3||1|deletion|1|HGNC|7000||||ENSP00000403206||B7Z4T5_HUMAN|UPI0001914F9B||||||||||||||||||||||||||CGAAC|,CT|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000495021|protein_coding||2/11|ENST00000495021.2:c.186+83_186+84insCT||||||||2||1|deletion|1|HGNC|7000||||ENSP00000440571||Q8IZZ2_HUMAN&Q53R57_HUMAN&F5GYS8_HUMAN|UPI0002065530||||||||||||||||||||||||||CGAAC|,C|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000398506|protein_coding||2/10|ENST00000398506.2:c.375+83_375+84insC||||||||3||1|deletion|1|HGNC|7000||||ENSP00000381518|MEIS1_HUMAN|Q53R57_HUMAN|UPI00005BDA98||||||||||||||||||||||||||CGAAC|,-|intron_variant&NMD_transcript_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000491706|nonsense_mediated_decay||2/4|ENST00000491706.1:c.240-494_240-493delAA||||||||1||1|deletion|1|HGNC|7000||||ENSP00000475875||U3KQH3_HUMAN|UPI00038BAF5F||||||||||||||||||||||||||CGAAC|,C|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000560281|protein_coding||3/9|ENST00000560281.2:c.381+83_381+84insC||||||||3||1|deletion|1|HGNC|7000||||ENSP00000454209||Q53RD5_HUMAN&Q53R57_HUMAN&H0YNY8_HUMAN|UPI00022F8457||||||||||||||||||||||||||CGAAC|,CT|intron_variant&NMD_transcript_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000491706|nonsense_mediated_decay||2/4|ENST00000491706.1:c.240-495_240-494insCT||||||||2||1|deletion|1|HGNC|7000||||ENSP00000475875||U3KQH3_HUMAN|UPI00038BAF5F||||||||||||||||||||||||||CGAAC|,A|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000272369|protein_coding||3/12|ENST00000272369.9:c.381+83G>A||||||||4||1|deletion|1|HGNC|7000|YES||CCDS46309.1|ENSP00000272369|MEIS1_HUMAN|Q8IZZ2_HUMAN&Q53RD5_HUMAN&Q53R57_HUMAN|UPI000000DA5A||||||||||||||||||||||||||CGAAC|,-|intron_variant&non_coding_transcript_variant|MODIFIER|AC092669.1|ENSG00000244522|Transcript|ENST00000454595|antisense||1/1|ENST00000454595.1:n.340-380_340-379delTT||||||||1||-1|deletion|1|Clone_based_vega_gene||YES||||||||||||||||||||||||||||||||CGAAC|,C|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000409622|nonsense_mediated_decay|||||||||||3|29|1|deletion|1|HGNC|7000||||ENSP00000386514|||UPI00038BB0A2||||||||||||||||||||||||||CGAAC|,C|intron_variant&non_coding_transcript_variant|MODIFIER|AC092669.1|ENSG00000244522|Transcript|ENST00000454595|antisense||1/1|ENST00000454595.1:n.340-379_340-378insG||||||||3||-1|deletion|1|Clone_based_vega_gene||YES||||||||||||||||||||||||||||||||CGAAC|,A|intron_variant&non_coding_transcript_variant|MODIFIER|MEIS1-AS2|ENSG00000230749|Transcript|ENST00000439433|antisense||1/1|ENST00000439433.1:n.88+158C>T||||||||4||-1|deletion|1|HGNC|40370|YES||||||||||||||||||||||||||||||||CGAAC|,CT|intron_variant&non_coding_transcript_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000490726|processed_transcript||3/4|ENST00000490726.1:n.396+83_396+84insCT||||||||2||1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,C|intron_variant&non_coding_transcript_variant|MODIFIER|MEIS1-AS2|ENSG00000230749|Transcript|ENST00000439433|antisense||1/1|ENST00000439433.1:n.88+157_88+158insG||||||||3||-1|deletion|1|HGNC|40370|YES||||||||||||||||||||||||||||||||CGAAC|,CT|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000606455|protein_coding|||||||||||2|2897|1|deletion|1|HGNC|7000||||ENSP00000475982||U3KQK9_HUMAN&Q53R57_HUMAN|UPI00038BB067||||||||||||||||||||||||||CGAAC|,CT|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000437869|protein_coding|||||||||||2|610|1|deletion|1|HGNC|7000||||ENSP00000397418|||UPI00038BAF56||||||||||||||||||||||||||CGAAC|,C|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000272369|protein_coding||3/12|ENST00000272369.9:c.381+83_381+84insC||||||||3||1|deletion|1|HGNC|7000|YES||CCDS46309.1|ENSP00000272369|MEIS1_HUMAN|Q8IZZ2_HUMAN&Q53RD5_HUMAN&Q53R57_HUMAN|UPI000000DA5A||||||||||||||||||||||||||CGAAC|,A|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000606455|protein_coding|||||||||||4|2898|1|deletion|1|HGNC|7000||||ENSP00000475982||U3KQK9_HUMAN&Q53R57_HUMAN|UPI00038BB067||||||||||||||||||||||||||CGAAC|,A|upstream_gene_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000498705|retained_intron|||||||||||4|2708|1|deletion|1|HGNC|7000|||||||||||||||||||||||||||||||||CGAAC|,A|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000495021|protein_coding||2/11|ENST00000495021.2:c.186+83G>A||||||||4||1|deletion|1|HGNC|7000||||ENSP00000440571||Q8IZZ2_HUMAN&Q53R57_HUMAN&F5GYS8_HUMAN|UPI0002065530||||||||||||||||||||||||||CGAAC|,A|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000407092|protein_coding||3/11|ENST00000407092.2:c.381+83G>A||||||||4||1|deletion|1|HGNC|7000||||ENSP00000384461||Q53RD5_HUMAN&Q53R57_HUMAN&F8W8U3_HUMAN|UPI00002085A6||||||||||||||||||||||||||CGAAC|,-|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000398506|protein_coding||2/10|ENST00000398506.2:c.375+84_375+85delAA||||||||1||1|deletion|1|HGNC|7000||||ENSP00000381518|MEIS1_HUMAN|Q53R57_HUMAN|UPI00005BDA98||||||||||||||||||||||||||CGAAC|,-|intron_variant|MODIFIER|MEIS1|ENSG00000143995|Transcript|ENST00000272369|protein_coding||3/12|ENST00000272369.9:c.381+84_381+85delAA||||||||1||1|deletion|1|HGNC|7000|YES||CCDS46309.1|ENSP00000272369|MEIS1_HUMAN|Q8IZZ2_HUMAN&Q53RD5_HUMAN&Q53R57_HUMAN|UPI000000DA5A||||||||||||||||||||||||||CGAAC|,CT|intron_variant&non_coding_transcript_variant|MODIFIER|AC092669.1|ENSG00000244522|Transcript|ENST00000454595|antisense||1/1|ENST00000454595.1:n.340-379_340-378insAG||||||||2||-1|deletion|1|Clone_based_vega_gene||YES||||||||||||||||||||||||||||||||CGAAC|,-|regulatory_region_variant|MODIFIER|||RegulatoryFeature|ENSR00000591896|promoter|||||||||||1|||deletion|1|||||||||||||||||||||||||||||||||||CGAAC|,C|regulatory_region_variant|MODIFIER|||RegulatoryFeature|ENSR00000591896|promoter|||||||||||3|||deletion|1|||||||||||||||||||||||||||||||||||CGAAC|,CT|regulatory_region_variant|MODIFIER|||RegulatoryFeature|ENSR00000591896|promoter|||||||||||2|||deletion|1|||||||||||||||||||||||||||||||||||CGAAC|,A|regulatory_region_variant|MODIFIER|||RegulatoryFeature|ENSR00000591896|promoter|||||||||||4|||deletion|1|||||||||||||||||||||||||||||||||||CGAAC|;AC_POPMAX=NA,9,1,NA;AN_POPMAX=NA,1710,1710,NA;POPMAX=NA,NFE,NFE,NA;K1_RUN=A:0,G:0,G:0,G:0;K2_RUN=GA:0,GA:0,GA:0,GA:0;K3_RUN=GAA:0,GAA:0,GAA:0,GAA:0;ESP_AF_POPMAX=0,NA,NA,NA;ESP_AF_GLOBAL=0,NA,NA,NA;ESP_AC=0,NA,NA,NA;KG_AF_POPMAX=0,NA,NA,NA;KG_AF_GLOBAL=0,NA,NA,NA;KG_AC=0,NA,NA,NA";
        List<Allele> alleles = parseLine(line);

        assertThat(alleles.size(), equalTo(4));

        Allele allele1 = alleles.get(0);
        System.out.println(allele1.toString());
        assertThat(allele1.getChr(), equalTo(2));
        assertThat(allele1.getPos(), equalTo(66667199));
        assertThat(allele1.getRsId(), equalTo("rs144159761"));
        assertThat(allele1.getRef(), equalTo("GAA"));
        assertThat(allele1.getAlt(), equalTo("G"));
        assertThat(allele1.getValues(), equalTo(Collections.emptyMap()));

        Map<AlleleProperty, Float> values2 = new EnumMap<>(AlleleProperty.class);
        //AC_NFE=0,9,1,0/AN_NFE=1710 *100
        values2.put(EXAC_NFE, 0.5263158f);

        Allele allele2 = alleles.get(1);
        System.out.println(allele2.toString());
        assertThat(allele2.getChr(), equalTo(2));
        assertThat(allele2.getPos(), equalTo(66667199));
        assertThat(allele2.getRsId(), equalTo("rs144159761"));
        assertThat(allele2.getRef(), equalTo("G"));
        assertThat(allele2.getAlt(), equalTo("GCT"));
        assertThat(allele2.getValues(), equalTo(values2));

        Map<AlleleProperty, Float> values3 = new EnumMap<>(AlleleProperty.class);
        //AC_NFE=0,9,1,0/AN_NFE=1710 * 100
        values3.put(EXAC_NFE, 0.058479533f);
        //AC_OTH=0,0,2,0/AN_OTH=46 * 100
        values3.put(EXAC_OTH, 4.347826f);

        Allele allele3 = alleles.get(2);
        System.out.println(allele3.toString());
        assertThat(allele3.getChr(), equalTo(2));
        assertThat(allele3.getPos(), equalTo(66667199));
        assertThat(allele3.getRsId(), equalTo("rs144159761"));
        assertThat(allele3.getRef(), equalTo("G"));
        assertThat(allele3.getAlt(), equalTo("GC"));
        assertThat(allele3.getValues(), equalTo(values3));

        Allele allele4 = alleles.get(3);
        System.out.println(allele4.toString());
        assertThat(allele4.getChr(), equalTo(2));
        assertThat(allele4.getPos(), equalTo(66667199));
        assertThat(allele4.getRsId(), equalTo("rs144159761"));
        assertThat(allele4.getRef(), equalTo("G"));
        assertThat(allele4.getAlt(), equalTo("A"));
        assertThat(allele4.getValues(), equalTo(Collections.emptyMap()));

    }

    @Test
    public void testParseDeletion() throws Exception {
        String line = "1\t32677658\t.\tCT\tC\t2410.42\tPASS\tAC=2;AC_AFR=0;AC_AMR=1;AC_Adj=2;AC_EAS=0;AC_FIN=0;AC_Het=2;AC_Hom=0;AC_NFE=1;AC_OTH=0;AC_SAS=0;AF=1.655e-05;AN=120856;AN_AFR=9596;AN_AMR=11182;AN_Adj=116590;AN_EAS=8414;AN_FIN=6544;AN_NFE=64694;AN_OTH=876;AN_SAS=15284;BaseQRankSum=1.07;ClippingRankSum=-4.000e-01;DP=1519079;FS=4.780;GQ_MEAN=64.32;GQ_STDDEV=20.54;Het_AFR=0;Het_AMR=1;Het_EAS=0;Het_FIN=0;Het_NFE=1;Het_OTH=0;Het_SAS=0;Hom_AFR=0;Hom_AMR=0;Hom_EAS=0;Hom_FIN=0;Hom_NFE=0;Hom_OTH=0;Hom_SAS=0;InbreedingCoeff=-0.0005;MQ=60.00;MQ0=0;MQRankSum=-3.030e-01;NCC=1458;QD=11.59;ReadPosRankSum=0.031;VQSLOD=1.91;culprit=FS;DP_HIST=97|1923|3061|1284|29454|13155|6129|2121|1061|681|411|258|162|130|100|77|72|50|45|157,0|0|0|0|1|0|1|0|0|0|0|0|0|0|0|0|0|0|0|0;GQ_HIST=34|117|40|62|2415|1039|1374|963|416|535|558|586|28263|7156|3123|4039|2713|1160|1409|4426,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|2;DOUBLETON_DIST=0.094369168694;AC_MALE=0;AC_FEMALE=2;AN_MALE=64996;AN_FEMALE=51594;AC_CONSANGUINEOUS=0;AN_CONSANGUINEOUS=1964;Hom_CONSANGUINEOUS=0;CSQ=-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000309777|protein_coding|||||||||||1|4139|-1|deletion|1|HGNC|28837|YES||CCDS356.2|ENSP00000309792|TM234_HUMAN||UPI000006F2AF||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000485689|processed_transcript|||||||||||1|4531|-1|deletion|1|HGNC|28837|||||||||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000484490|nonsense_mediated_decay|||||||||||1|4299|-1|deletion|1|HGNC|28837||||ENSP00000432747||E9PNU5_HUMAN|UPI0001F77A2C||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000495091|processed_transcript|||||||||||1|2419|-1|deletion|1|HGNC|28837|||||||||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|IQCC|ENSG00000160051|Transcript|ENST00000291358|protein_coding|||||||||||1|3371|1|deletion|1|HGNC|25545|||CCDS355.1|ENSP00000291358|IQCC_HUMAN||UPI000013E040||||||||||||||||||||||||||CTT|,-|non_coding_transcript_exon_allele&non_coding_transcript_allele|MODIFIER|DCDC2B|ENSG00000222046|Transcript|ENST00000487056|retained_intron|3/7||ENST00000487056.1:n.618delT||614||||||1||1|deletion|1|HGNC|32576|||||||||||4||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|IQCC|ENSG00000160051|Transcript|ENST00000537469|protein_coding|||||||||||1|3371|1|deletion|1|HGNC|25545|YES||CCDS53293.1|ENSP00000442291|IQCC_HUMAN||UPI0001A42065||||||||||||||||||||||||||CTT|,-|intron_allele|MODIFIER|DCDC2B|ENSG00000222046|Transcript|ENST00000409358|protein_coding||3/8|ENST00000409358.1:c.396-8delT||||||||1||1|deletion|1|HGNC|32576|YES||CCDS44100.1|ENSP00000386870|DCD2B_HUMAN||UPI0000DD78AB||||4||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000483001|nonsense_mediated_decay|||||||||||1|3789|-1|deletion|1|HGNC|28837||||ENSP00000434295|TM234_HUMAN||UPI0000073C70||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000491434|nonsense_mediated_decay|||||||||||1|2419|-1|deletion|1|HGNC|28837||||ENSP00000435588|TM234_HUMAN||UPI0000D473AA||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000545122|protein_coding|||||||||||1|4038|-1|deletion|1|HGNC|28837||||ENSP00000442664||B4DHR3_HUMAN|UPI00017A7341||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000344461|protein_coding|||||||||||1|4142|-1|deletion|1|HGNC|28837||||ENSP00000344021|TM234_HUMAN||UPI0000073C70||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000373593|protein_coding|||||||||||1|4139|-1|deletion|1|HGNC|28837||||ENSP00000362695|TM234_HUMAN||UPI0000048EE9||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000487174|retained_intron|||||||||||1|4672|-1|deletion|1|HGNC|28837|||||||||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000489170|nonsense_mediated_decay|||||||||||1|2419|-1|deletion|1|HGNC|28837||||ENSP00000436631|TM234_HUMAN||UPI0000073C70||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000461402|nonsense_mediated_decay|||||||||||1|2420|-1|deletion|1|HGNC|28837||||ENSP00000433020|TM234_HUMAN||UPI0000073C70||||||||||||||||||||||||||CTT|,-|downstream_gene_allele|MODIFIER|TMEM234|ENSG00000160055|Transcript|ENST00000466796|retained_intron|||||||||||1|2414|-1|deletion|1|HGNC|28837|||||||||||||||||||||||||||||||||CTT|;AC_POPMAX=1;AN_POPMAX=11182;POPMAX=AMR;K1_RUN=T:4;K2_RUN=TT:1;K3_RUN=TTT:0;ESP_AF_POPMAX=0;ESP_AF_GLOBAL=0;ESP_AC=0;KG_AF_POPMAX=0;KG_AF_GLOBAL=0;KG_AC=0";
        List<Allele> alleles = parseLine(line);

        assertThat(alleles.size(), equalTo(1));

        Map<AlleleProperty, Float> values = new EnumMap<>(AlleleProperty.class);
        //1/11182 *100
        values.put(EXAC_AMR, 0.008942944f);
        //1/64694 * 100
        values.put(EXAC_NFE, 0.0015457384f);

        Allele allele = alleles.get(0);
        System.out.println(allele.toString());
        assertThat(allele.getChr(), equalTo(1));
        assertThat(allele.getPos(), equalTo(32677658));
        assertThat(allele.getRsId(), equalTo("."));
        assertThat(allele.getRef(), equalTo("CT"));
        assertThat(allele.getAlt(), equalTo("C"));
        assertThat(allele.getValues(), equalTo(values));
    }

    @Test
    public void testParseInsertion() throws Exception {
        String line = "1\t36208758\t.\tT\tTCA\t3051.85\tPASS\tAC=1;AC_AFR=0;AC_AMR=0;AC_Adj=1;AC_EAS=1;AC_FIN=0;AC_Het=1;AC_Hom=0;AC_NFE=0;AC_OTH=0;AC_SAS=0;AF=8.237e-06;AN=121410;AN_AFR=10152;AN_AMR=11506;AN_Adj=120242;AN_EAS=8608;AN_FIN=6612;AN_NFE=66058;AN_OTH=898;AN_SAS=16408;BaseQRankSum=5.86;ClippingRankSum=0.406;DP=2978660;FS=1.109;GQ_MEAN=100.28;GQ_STDDEV=24.78;Het_AFR=0;Het_AMR=0;Het_EAS=1;Het_FIN=0;Het_NFE=0;Het_OTH=0;Het_SAS=0;Hom_AFR=0;Hom_AMR=0;Hom_EAS=0;Hom_FIN=0;Hom_NFE=0;Hom_OTH=0;Hom_SAS=0;InbreedingCoeff=-0.0001;MQ=59.32;MQ0=0;MQRankSum=0.903;NCC=2;QD=12.66;ReadPosRankSum=0.089;VQSLOD=0.701;culprit=MQ;DP_HIST=30|551|90|24|5467|4605|5527|6556|7135|6968|5702|4514|3262|2231|1580|1197|838|651|519|3258,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1;GQ_HIST=1|32|3|10|525|45|37|22|6|13|3|3|7800|2289|1240|2164|2409|1583|2667|39853,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1;DOUBLETON_DIST=.;AC_MALE=0;AC_FEMALE=1;AN_MALE=67090;AN_FEMALE=53152;AC_CONSANGUINEOUS=0;AN_CONSANGUINEOUS=2160;Hom_CONSANGUINEOUS=0;CSQ=C|missense_allele|MODERATE|CLSPN|ENSG00000092853|Transcript|ENST00000318121|protein_coding|18/25||ENST00000318121.3:c.3127A>G|ENSP00000312995.3:p.Lys1043Glu|3185|3127|1043|K/E|Aag/Gag||1||-1|SNV|1|HGNC|19715|YES||CCDS396.1|ENSP00000312995|CLSPN_HUMAN||UPI0000161087|tolerated(0.38)|benign(0.149)|hmmpanther:PTHR14396|||||||||||||||||||||||TTC|T,C|downstream_gene_allele|MODIFIER|CLSPN|ENSG00000092853|Transcript|ENST00000517467|retained_intron|||||||||||1|3808|-1|SNV|1|HGNC|19715|||||||||||||||||||||||||||||||||TTC|T,C|missense_allele|MODERATE|CLSPN|ENSG00000092853|Transcript|ENST00000520551|protein_coding|18/25||ENST00000520551.1:c.2968A>G|ENSP00000428848.1:p.Lys990Glu|3026|2968|990|K/E|Aag/Gag||1||-1|SNV|1|HGNC|19715||||ENSP00000428848||E7ESG2_HUMAN|UPI0000203F04|tolerated(0.82)|possibly_damaging(0.888)|hmmpanther:PTHR14396|||||||||||||||||||||||TTC|T,C|missense_allele|MODERATE|CLSPN|ENSG00000092853|Transcript|ENST00000373220|protein_coding|17/24||ENST00000373220.3:c.2935A>G|ENSP00000362317.3:p.Lys979Glu|2993|2935|979|K/E|Aag/Gag||1||-1|SNV|1|HGNC|19715|||CCDS53297.1|ENSP00000362317|CLSPN_HUMAN||UPI00015E0B59|tolerated(0.2)|benign(0.149)|hmmpanther:PTHR14396|||||||||||||||||||||||TTC|T,C|splice_region_allele&intron_allele&non_coding_transcript_allele|LOW|RP11-435D7.3|ENSG00000232335|Transcript|ENST00000373226|antisense||1/1|ENST00000373226.2:n.126-6T>C||||||||1||1|SNV|1|Clone_based_vega_gene||YES||||||||||||||||||||||||||||||||TTC|T,C|missense_allele|MODERATE|CLSPN|ENSG00000092853|Transcript|ENST00000251195|protein_coding|18/25||ENST00000251195.5:c.3127A>G|ENSP00000251195.5:p.Lys1043Glu|3224|3127|1043|K/E|Aag/Gag||1||-1|SNV|1|HGNC|19715||||ENSP00000251195|CLSPN_HUMAN||UPI00004561AE|tolerated(0.44)|benign(0.149)|hmmpanther:PTHR14396|||||||||||||||||||||||TTC|T;AC_POPMAX=1;AN_POPMAX=8608;POPMAX=EAS;K1_RUN=T:0;K2_RUN=TC:1;K3_RUN=TCT:0;ESP_AF_POPMAX=0;ESP_AF_GLOBAL=0;ESP_AC=0;KG_AF_POPMAX=0;KG_AF_GLOBAL=0;KG_AC=0";
        List<Allele> alleles = parseLine(line);

        assertThat(alleles.size(), equalTo(1));

        Map<AlleleProperty, Float> values = new EnumMap<>(AlleleProperty.class);
        //1/8608 *100
        values.put(EXAC_EAS, 0.011617101f);

        Allele allele = alleles.get(0);
        System.out.println(allele.toString());
        assertThat(allele.getChr(), equalTo(1));
        assertThat(allele.getPos(), equalTo(36208758));
        assertThat(allele.getRsId(), equalTo("."));
        assertThat(allele.getRef(), equalTo("T"));
        assertThat(allele.getAlt(), equalTo("TCA"));
        assertThat(allele.getValues(), equalTo(values));
    }

    @Test
    public void testParseX() throws Exception {
        String line = "X\t21226476\t.\tC\tT\t20713.01\tPASS\tAC=27;AC_AFR=0;AC_AMR=0;AC_Adj=26;AC_EAS=0;AC_FIN=0;AC_Het=26;AC_Hom=0;AC_NFE=18;AC_OTH=0;AC_SAS=8;AF=2.227e-04;AN=121260;AN_AFR=9376;AN_AMR=11384;AN_Adj=114318;AN_EAS=8436;AN_FIN=6598;AN_NFE=64412;AN_OTH=836;AN_SAS=13276;BaseQRankSum=3.70;ClippingRankSum=-1.270e-01;DP=2751238;FS=2.986;GQ_MEAN=88.52;GQ_STDDEV=36.63;Het_AFR=0;Het_AMR=0;Het_EAS=0;Het_FIN=0;Het_NFE=18;Het_OTH=0;Het_SAS=8;Hom_AFR=0;Hom_AMR=0;Hom_EAS=0;Hom_FIN=0;Hom_NFE=0;Hom_OTH=0;Hom_SAS=0;InbreedingCoeff=-0.0011;MQ=59.79;MQ0=0;MQRankSum=0.296;NCC=197;QD=13.56;ReadPosRankSum=-1.510e-01;VQSLOD=1.95;culprit=FS;DP_HIST=788|2470|3638|1713|597|3207|5651|6312|6867|6638|5604|4474|3269|2428|1669|1169|869|607|502|2158,0|1|1|3|2|1|0|4|1|1|1|0|1|4|3|1|0|1|1|1;GQ_HIST=166|999|469|479|3434|954|1126|653|240|238|82|63|3815|2710|1422|2619|2889|1671|3271|33330,0|0|0|0|0|0|0|0|0|0|0|0|0|0|1|0|0|0|0|26;DOUBLETON_DIST=.;AC_MALE=17;AC_FEMALE=9;AN_MALE=63560;AN_FEMALE=50758;AC_CONSANGUINEOUS=1;AN_CONSANGUINEOUS=1552;Hom_CONSANGUINEOUS=0;CSQ=T|intron_allele|MODIFIER|EIF4G3|ENSG00000075151|Transcript|ENST00000544689|protein_coding||3/5|ENST00000544689.1:c.214-40G>A|||||||rs374520420|1||-1|SNV|1|HGNC|3298||||ENSP00000444401||Q5SWD1_HUMAN|UPI000046FF62|||||T:0.0002|T:0.0008|T:0||T:0|T:0|T:0|T:0|T:0.0003|||||||||||||ACG|C,T|intron_allele|MODIFIER|EIF4G3|ENSG00000075151|Transcript|ENST00000374935|protein_coding||8/29|ENST00000374935.3:c.745-40G>A|||||||rs374520420|1||-1|SNV|1|HGNC|3298||||ENSP00000364071||Q504Z1_HUMAN&B1AN91_HUMAN|UPI000050D019|||||T:0.0002|T:0.0008|T:0||T:0|T:0|T:0|T:0|T:0.0003|||||||||||||ACG|C,T|intron_allele&non_coding_transcript_allele|MODIFIER|EIF4G3|ENSG00000075151|Transcript|ENST00000374933|processed_transcript||2/4|ENST00000374933.3:n.230-40G>A|||||||rs374520420|1||-1|SNV|1|HGNC|3298||||||||||||T:0.0002|T:0.0008|T:0||T:0|T:0|T:0|T:0|T:0.0003|||||||||||||ACG|C,T|intron_allele|MODIFIER|EIF4G3|ENSG00000075151|Transcript|ENST00000400422|protein_coding||12/33|ENST00000400422.1:c.1585-40G>A|||||||rs374520420|1||-1|SNV|1|HGNC|3298|||CCDS214.1|ENSP00000383274|IF4G3_HUMAN|F5H564_HUMAN&B1AN91_HUMAN|UPI0000070825|||||T:0.0002|T:0.0008|T:0||T:0|T:0|T:0|T:0|T:0.0003|||||||||||||ACG|C,T|upstream_gene_allele|MODIFIER|EIF4G3|ENSG00000075151|Transcript|ENST00000537738|protein_coding||||||||||rs374520420|1|70|-1|SNV|1|HGNC|3298||||ENSP00000442010||F5H8J4_HUMAN|UPI0002065A57|||||T:0.0002|T:0.0008|T:0||T:0|T:0|T:0|T:0|T:0.0003|||||||||||||ACG|C,T|intron_allele&non_coding_transcript_allele|MODIFIER|EIF4G3|ENSG00000075151|Transcript|ENST00000496705|processed_transcript||1/4|ENST00000496705.1:n.56-40G>A|||||||rs374520420|1||-1|SNV|1|HGNC|3298||||||||||||T:0.0002|T:0.0008|T:0||T:0|T:0|T:0|T:0|T:0.0003|||||||||||||ACG|C,T|intron_allele|MODIFIER|EIF4G3|ENSG00000075151|Transcript|ENST00000374937|protein_coding||10/31|ENST00000374937.3:c.1603-40G>A|||||||rs374520420|1||-1|SNV|1|HGNC|3298|||CCDS55580.1|ENSP00000364073|IF4G3_HUMAN|Q59GJ0_HUMAN&F5H564_HUMAN|UPI0001639589|||||T:0.0002|T:0.0008|T:0||T:0|T:0|T:0|T:0|T:0.0003|||||||||||||ACG|C,T|intron_allele|MODIFIER|EIF4G3|ENSG00000075151|Transcript|ENST00000536266|protein_coding||4/25|ENST00000536266.1:c.397-40G>A|||||||rs374520420|1||-1|SNV|1|HGNC|3298||||ENSP00000444693||F5H564_HUMAN|UPI0002065A56|||||T:0.0002|T:0.0008|T:0||T:0|T:0|T:0|T:0|T:0.0003|||||||||||||ACG|C,T|intron_allele|MODIFIER|EIF4G3|ENSG00000075151|Transcript|ENST00000602326|protein_coding||13/34|ENST00000602326.1:c.1603-40G>A|||||||rs374520420|1||-1|SNV|1|HGNC|3298|YES||CCDS55580.1|ENSP00000473510|IF4G3_HUMAN|Q59GJ0_HUMAN&F5H564_HUMAN|UPI0001639589|||||T:0.0002|T:0.0008|T:0||T:0|T:0|T:0|T:0|T:0.0003|||||||||||||ACG|C,T|intron_allele|MODIFIER|EIF4G3|ENSG00000075151|Transcript|ENST00000264211|protein_coding||9/30|ENST00000264211.8:c.1585-40G>A|||||||rs374520420|1||-1|SNV|1|HGNC|3298|||CCDS214.1|ENSP00000264211|IF4G3_HUMAN|F5H564_HUMAN&B1AN91_HUMAN|UPI0000070825|||||T:0.0002|T:0.0008|T:0||T:0|T:0|T:0|T:0|T:0.0003|||||||||||||ACG|C;AC_POPMAX=8;AN_POPMAX=13276;POPMAX=SAS;K1_RUN=C:0;K2_RUN=CG:0;K3_RUN=CGG:0;ESP_AF_POPMAX=0.00034908074303530157;ESP_AF_GLOBAL=0.0002312316937604919;ESP_AC=3;KG_AF_POPMAX=0.0007999999797903001;KG_AF_GLOBAL=0.00019968050764873624;KG_AC=1";
        List<Allele> alleles = parseLine(line);

        assertThat(alleles.size(), equalTo(1));

        Map<AlleleProperty, Float> values = new EnumMap<>(AlleleProperty.class);
        //AC_NFE=2/AN_NFE=66718 * 100
        values.put(EXAC_NFE, 0.027945103f);
        values.put(EXAC_SAS, 0.060259115f);

        Allele allele = alleles.get(0);
        System.out.println(allele.toString());
        assertThat(allele.getChr(), equalTo(23));
        assertThat(allele.getPos(), equalTo(21226476));
        assertThat(allele.getRsId(), equalTo("."));
        assertThat(allele.getRef(), equalTo("C"));
        assertThat(allele.getAlt(), equalTo("T"));
        assertThat(allele.getValues(), equalTo(values));
    }

    @Test
    public void testParseY() throws Exception {
        String line = "Y\t39878935\t.\tC\tT\t324.65\tPASS\tAC=1;AC_AFR=0;AC_AMR=0;AC_Adj=1;AC_EAS=0;AC_FIN=0;AC_Het=1;AC_Hom=0;AC_NFE=1;AC_OTH=0;AC_SAS=0;AF=8.284e-06;AN=120718;AN_AFR=6972;AN_AMR=8610;AN_Adj=94776;AN_EAS=6868;AN_FIN=4160;AN_NFE=53612;AN_OTH=678;AN_SAS=13876;BaseQRankSum=-2.709e+00;ClippingRankSum=1.07;DP=1064143;FS=0.000;GQ_MEAN=42.86;GQ_STDDEV=19.97;Het_AFR=0;Het_AMR=0;Het_EAS=0;Het_FIN=0;Het_NFE=1;Het_OTH=0;Het_SAS=0;Hom_AFR=0;Hom_AMR=0;Hom_EAS=0;Hom_FIN=0;Hom_NFE=0;Hom_OTH=0;Hom_SAS=0;InbreedingCoeff=-0.0038;MQ=50.21;MQ0=0;MQRankSum=-4.410e-01;NCC=1697;NEGATIVE_TRAIN_SITE;QD=19.10;ReadPosRankSum=-9.450e-01;VQSLOD=-1.286e+00;culprit=FS;DP_HIST=907|11748|12176|2502|22950|8258|1533|232|39|5|6|2|0|0|1|0|0|0|0|0,0|0|0|1|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0;GQ_HIST=100|1049|184|383|20665|1969|1619|942|444|557|825|851|28004|2064|353|235|70|21|16|8,0|0|0|0|0|0|0|0|0|0|0|0|0|0|1|0|0|0|0|0;DOUBLETON_DIST=.;AC_MALE=1;AC_FEMALE=0;AN_MALE=53894;AN_FEMALE=40882;AC_CONSANGUINEOUS=0;AN_CONSANGUINEOUS=1918;Hom_CONSANGUINEOUS=0;CSQ=T|intron_allele|MODIFIER|MACF1|ENSG00000127603|Transcript|ENST00000372925|protein_coding||31/70|ENST00000372925.2:c.6968-9111C>T||||||||1||1|SNV|1|HGNC|13664||||ENSP00000362016||Q8WXY0_HUMAN&Q8WXX9_HUMAN&B4DNC4_HUMAN|UPI000059DC15||||||||||||||||||||||||||GCC|C,T|intron_allele|MODIFIER|MACF1|ENSG00000127603|Transcript|ENST00000361689|protein_coding||52/92|ENST00000361689.2:c.9631-9111C>T||||||||1||1|SNV|1|HGNC|13664|||CCDS435.1|ENSP00000354573|MACF1_HUMAN|Q8WXY4_HUMAN&Q8WXY3_HUMAN&Q8WXY0_HUMAN&Q8WXX9_HUMAN&B4DNC4_HUMAN|UPI00001B3DC6||||||||||||||||||||||||||GCC|C,T|intron_allele|MODIFIER|MACF1|ENSG00000127603|Transcript|ENST00000289893|protein_coding||22/63|ENST00000289893.4:c.11137-9111C>T||||||||1||1|SNV|1|HGNC|13664||||ENSP00000289893|MACF1_HUMAN|Q8WXY0_HUMAN&Q8WXX9_HUMAN&Q6IPG6_HUMAN&B4DNC4_HUMAN|UPI00001B3DC5||||||||||||||||||||||||||GCC|C,T|intron_allele|MODIFIER|MACF1|ENSG00000127603|Transcript|ENST00000564288|protein_coding||58/100|ENST00000564288.1:c.15817-9111C>T||||||||1||1|SNV|1|HGNC|13664||||ENSP00000455274||Q8WXY4_HUMAN&Q8WXY3_HUMAN&Q8WXY0_HUMAN&Q8WXX9_HUMAN&H3BPE1_HUMAN|UPI0002467516||||||||||||||||||||||||||GCC|C,T|intron_allele|MODIFIER|MACF1|ENSG00000127603|Transcript|ENST00000539005|protein_coding||51/90|ENST00000539005.1:c.9568-9111C>T||||||||1||1|SNV|1|HGNC|13664||||ENSP00000444364||Q8WXY4_HUMAN&Q8WXY3_HUMAN&Q8WXY0_HUMAN&Q8WXX9_HUMAN&F5GZL7_HUMAN&B4DNC4_HUMAN|UPI0000D626C7||||||||||||||||||||||||||GCC|C,T|intron_allele|MODIFIER|MACF1|ENSG00000127603|Transcript|ENST00000372915|protein_coding||57/96|ENST00000372915.3:c.15832-9111C>T||||||||1||1|SNV|1|HGNC|13664||||ENSP00000362006|MACF1_HUMAN|Q8WXY4_HUMAN&Q8WXY3_HUMAN&Q8WXY0_HUMAN&Q8WXX9_HUMAN&B4DNC4_HUMAN|UPI0001F78894||||||||||||||||||||||||||GCC|C,T|intron_allele|MODIFIER|MACF1|ENSG00000127603|Transcript|ENST00000317713|protein_coding||52/92|ENST00000317713.7:c.9631-9111C>T||||||||1||1|SNV|1|HGNC|13664||||ENSP00000313438||Q8WXY4_HUMAN&Q8WXY3_HUMAN&Q8WXY0_HUMAN&Q8WXX9_HUMAN&F8W8Q1_HUMAN&B4DNC4_HUMAN|UPI00004578EC||||||||||||||||||||||||||GCC|C,T|upstream_gene_allele|MODIFIER|MACF1|ENSG00000127603|Transcript|ENST00000482035|protein_coding|||||||||||1|1137|1|SNV|1|HGNC|13664||||ENSP00000433104||H0YD69_HUMAN|UPI00000731D5||||||||||||||||||||||||||GCC|C,T|intron_allele|MODIFIER|MACF1|ENSG00000127603|Transcript|ENST00000567887|protein_coding||58/100|ENST00000567887.1:c.15928-9111C>T||||||||1||1|SNV|1|HGNC|13664||||ENSP00000455823||Q8WXY4_HUMAN&Q8WXY3_HUMAN&Q8WXY0_HUMAN&Q8WXX9_HUMAN&H3BQK9_HUMAN|UPI0002467515||||||||||||||||||||||||||GCC|C,T|intron_allele|MODIFIER|MACF1|ENSG00000127603|Transcript|ENST00000545844|protein_coding||53/93|ENST00000545844.1:c.9631-9111C>T||||||||1||1|SNV|1|HGNC|13664|YES||CCDS435.1|ENSP00000439537|MACF1_HUMAN|Q8WXY4_HUMAN&Q8WXY3_HUMAN&Q8WXY0_HUMAN&Q8WXX9_HUMAN&B4DNC4_HUMAN|UPI00001B3DC6||||||||||||||||||||||||||GCC|C,T|missense_allele|MODERATE|KIAA0754|ENSG00000255103|Transcript|ENST00000530275|protein_coding|1/1||ENST00000530275.1:c.2590C>T|ENSP00000431179.1:p.Pro864Ser|2785|2590|864|P/S|Ccc/Tcc||1||1|SNV|1|HGNC|29111|YES|||ENSP00000431179|K0754_HUMAN||UPI0000DD78B2||probably_damaging(0.932)|Low_complexity_(Seg):seg|||||||||||||||||||||||GCC|C;AC_POPMAX=1;AN_POPMAX=53612;POPMAX=NFE;K1_RUN=C:2;K2_RUN=CC:0;K3_RUN=CCC:0;ESP_AF_POPMAX=0;ESP_AF_GLOBAL=0;ESP_AC=0;KG_AF_POPMAX=0;KG_AF_GLOBAL=0;KG_AC=0";
        List<Allele> alleles = parseLine(line);

        assertThat(alleles.size(), equalTo(1));

        Map<AlleleProperty, Float> values = new EnumMap<>(AlleleProperty.class);
        //AC_NFE=2/AN_NFE=66718 * 100
        values.put(EXAC_NFE, 0.001865254f);

        Allele allele = alleles.get(0);
        System.out.println(allele.toString());
        assertThat(allele.getChr(), equalTo(24));
        assertThat(allele.getPos(), equalTo(39878935));
        assertThat(allele.getRsId(), equalTo("."));
        assertThat(allele.getRef(), equalTo("C"));
        assertThat(allele.getAlt(), equalTo("T"));
        assertThat(allele.getValues(), equalTo(values));
    }

    @Test
    public void testParseM() throws Exception {
        String line = "M\t38048436\t.\tA\tG\t5175.39\tPASS\tAC=2;AC_AFR=0;AC_AMR=0;AC_Adj=2;AC_EAS=0;AC_FIN=0;AC_Het=2;AC_Hom=0;AC_NFE=2;AC_OTH=0;AC_SAS=0;AF=1.647e-05;AN=121412;AN_AFR=10404;AN_AMR=11576;AN_Adj=121382;AN_EAS=8654;AN_FIN=6614;AN_NFE=66718;AN_OTH=908;AN_SAS=16508;BaseQRankSum=0.560;ClippingRankSum=0.939;DP=2029453;FS=0.788;GQ_MEAN=75.04;GQ_STDDEV=20.97;Het_AFR=0;Het_AMR=0;Het_EAS=0;Het_FIN=0;Het_NFE=2;Het_OTH=0;Het_SAS=0;Hom_AFR=0;Hom_AMR=0;Hom_EAS=0;Hom_FIN=0;Hom_NFE=0;Hom_OTH=0;Hom_SAS=0;InbreedingCoeff=-0.0001;MQ=59.78;MQ0=0;MQRankSum=0.343;NCC=3;QD=13.55;ReadPosRankSum=-3.260e-01;VQSLOD=1.80;culprit=ReadPosRankSum;DP_HIST=1|14|8|6|12087|20843|12530|5883|3021|1444|903|645|518|429|391|274|233|194|162|1120,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1|0|0|1;GQ_HIST=0|1|0|0|17|2|1|6|1|2|3|1|27766|7687|3283|4881|3675|1591|2366|9423,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|2;DOUBLETON_DIST=0.0301124558945;AC_MALE=1;AC_FEMALE=1;AN_MALE=67272;AN_FEMALE=54110;AC_CONSANGUINEOUS=0;AN_CONSANGUINEOUS=2164;Hom_CONSANGUINEOUS=0;CSQ=G|upstream_gene_allele|MODIFIER|GNL2|ENSG00000134697|Transcript|ENST00000469191|processed_transcript||||||||||rs371440333|1|26|-1|SNV|1|HGNC|29925|||||||||||||||||||G:0|G:0.0001|||||||||||||AAG|A,G|synonymous_allele|LOW|GNL2|ENSG00000134697|Transcript|ENST00000373062|protein_coding|7/16||ENST00000373062.3:c.738T>C|ENST00000373062.3:c.738T>C(p.%3D)|837|738|246|P|ccT/ccC|rs371440333|1||-1|SNV|1|HGNC|29925|YES||CCDS421.1|ENSP00000362153|NOG2_HUMAN|Q5T0F3_HUMAN|UPI0000000C9E|||Superfamily_domains:SSF52540&Gene3D:3.40.50.300&hmmpanther:PTHR11089:SF9&hmmpanther:PTHR11089&PROSITE_profiles:PS51721|||||||||G:0|G:0.0001|||||||||||||AAG|A,G|non_coding_transcript_exon_allele&non_coding_transcript_allele|MODIFIER|GNL2|ENSG00000134697|Transcript|ENST00000489146|processed_transcript|1/2||ENST00000489146.1:n.512T>C||512|||||rs371440333|1||-1|SNV|1|HGNC|29925|||||||||||||||||||G:0|G:0.0001|||||||||||||AAG|A,G|synonymous_allele|LOW|GNL2|ENSG00000134697|Transcript|ENST00000538069|protein_coding|1/6||ENST00000538069.1:c.89T>C|ENST00000538069.1:c.89T>C(p.%3D)|89|90|30|P|ccT/ccC|rs371440333|1||-1|SNV|1|HGNC|29925||||ENSP00000441288|||UPI000204AF2E|||PROSITE_profiles:PS51721&hmmpanther:PTHR11089&hmmpanther:PTHR11089:SF9&Gene3D:3.40.50.300&Superfamily_domains:SSF52540|||||||||G:0|G:0.0001|||||||||||||AAG|A;AC_POPMAX=2;AN_POPMAX=66718;POPMAX=NFE;K1_RUN=A:0;K2_RUN=AG:0;K3_RUN=AGG:0;ESP_AF_POPMAX=0.00011627907224465162;ESP_AF_GLOBAL=0.00007688759069424123;ESP_AC=1;KG_AF_POPMAX=0;KG_AF_GLOBAL=0;KG_AC=0";
        List<Allele> alleles = parseLine(line);

        assertThat(alleles.size(), equalTo(1));

        Map<AlleleProperty, Float> values = new EnumMap<>(AlleleProperty.class);
        values.put(EXAC_NFE, 0.0029976917f);

        Allele allele = alleles.get(0);
        System.out.println(allele.toString());
        assertThat(allele.getChr(), equalTo(25));
        assertThat(allele.getPos(), equalTo(38048436));
        assertThat(allele.getRsId(), equalTo("."));
        assertThat(allele.getRef(), equalTo("A"));
        assertThat(allele.getAlt(), equalTo("G"));
        assertThat(allele.getValues(), equalTo(values));
    }

    @Test
    public void testParseUnknownChromosome() throws Exception {
        String line = "CONT000211213\t38048436\t.\tA\tG\t5175.39\tPASS\tAC=2;AC_AFR=0;AC_AMR=0;AC_Adj=2;AC_EAS=0;AC_FIN=0;AC_Het=2;AC_Hom=0;AC_NFE=2;AC_OTH=0;AC_SAS=0;AF=1.647e-05;AN=121412;AN_AFR=10404;AN_AMR=11576;AN_Adj=121382;AN_EAS=8654;AN_FIN=6614;AN_NFE=66718;AN_OTH=908;AN_SAS=16508;BaseQRankSum=0.560;ClippingRankSum=0.939;DP=2029453;FS=0.788;GQ_MEAN=75.04;GQ_STDDEV=20.97;Het_AFR=0;Het_AMR=0;Het_EAS=0;Het_FIN=0;Het_NFE=2;Het_OTH=0;Het_SAS=0;Hom_AFR=0;Hom_AMR=0;Hom_EAS=0;Hom_FIN=0;Hom_NFE=0;Hom_OTH=0;Hom_SAS=0;InbreedingCoeff=-0.0001;MQ=59.78;MQ0=0;MQRankSum=0.343;NCC=3;QD=13.55;ReadPosRankSum=-3.260e-01;VQSLOD=1.80;culprit=ReadPosRankSum;DP_HIST=1|14|8|6|12087|20843|12530|5883|3021|1444|903|645|518|429|391|274|233|194|162|1120,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1|0|0|1;GQ_HIST=0|1|0|0|17|2|1|6|1|2|3|1|27766|7687|3283|4881|3675|1591|2366|9423,0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|2;DOUBLETON_DIST=0.0301124558945;AC_MALE=1;AC_FEMALE=1;AN_MALE=67272;AN_FEMALE=54110;AC_CONSANGUINEOUS=0;AN_CONSANGUINEOUS=2164;Hom_CONSANGUINEOUS=0;CSQ=G|upstream_gene_allele|MODIFIER|GNL2|ENSG00000134697|Transcript|ENST00000469191|processed_transcript||||||||||rs371440333|1|26|-1|SNV|1|HGNC|29925|||||||||||||||||||G:0|G:0.0001|||||||||||||AAG|A,G|synonymous_allele|LOW|GNL2|ENSG00000134697|Transcript|ENST00000373062|protein_coding|7/16||ENST00000373062.3:c.738T>C|ENST00000373062.3:c.738T>C(p.%3D)|837|738|246|P|ccT/ccC|rs371440333|1||-1|SNV|1|HGNC|29925|YES||CCDS421.1|ENSP00000362153|NOG2_HUMAN|Q5T0F3_HUMAN|UPI0000000C9E|||Superfamily_domains:SSF52540&Gene3D:3.40.50.300&hmmpanther:PTHR11089:SF9&hmmpanther:PTHR11089&PROSITE_profiles:PS51721|||||||||G:0|G:0.0001|||||||||||||AAG|A,G|non_coding_transcript_exon_allele&non_coding_transcript_allele|MODIFIER|GNL2|ENSG00000134697|Transcript|ENST00000489146|processed_transcript|1/2||ENST00000489146.1:n.512T>C||512|||||rs371440333|1||-1|SNV|1|HGNC|29925|||||||||||||||||||G:0|G:0.0001|||||||||||||AAG|A,G|synonymous_allele|LOW|GNL2|ENSG00000134697|Transcript|ENST00000538069|protein_coding|1/6||ENST00000538069.1:c.89T>C|ENST00000538069.1:c.89T>C(p.%3D)|89|90|30|P|ccT/ccC|rs371440333|1||-1|SNV|1|HGNC|29925||||ENSP00000441288|||UPI000204AF2E|||PROSITE_profiles:PS51721&hmmpanther:PTHR11089&hmmpanther:PTHR11089:SF9&Gene3D:3.40.50.300&Superfamily_domains:SSF52540|||||||||G:0|G:0.0001|||||||||||||AAG|A;AC_POPMAX=2;AN_POPMAX=66718;POPMAX=NFE;K1_RUN=A:0;K2_RUN=AG:0;K3_RUN=AGG:0;ESP_AF_POPMAX=0.00011627907224465162;ESP_AF_GLOBAL=0.00007688759069424123;ESP_AC=1;KG_AF_POPMAX=0;KG_AF_GLOBAL=0;KG_AC=0";
        List<Allele> alleles = parseLine(line);

        assertThat(alleles.size(), equalTo(0));
    }
}
