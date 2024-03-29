package NomenclatureFun;

import java.io.IOException;

public class Permutations {
	static String[][] oxyanions = {{"C2H3O3^-", "peracetate"}, {"C2H3O2^-", "acetate"}, {"C2H3O^-", "acetite"},
			{"ClO4^-", "perchlorate"}, {"ClO3^-", "chlorate"} , {"ClO2^-", "chlorite"}, {"ClO^-", "hypochlorite"},
			{"BrO4^-", "perbromate"}, {"BrO3^-", "bromate"}, {"BrO2^-", "bromite"}, {"BrO^-", "hypobromite"},
			{"IO4^-", "periodate"}, {"IO3^-", "iodate"}, {"IO2^-", "iodite"}, {"IO^-", "hypoiodite"},
			{"H2PO5^-", "dihydrogen perphosphate"}, {"H2PO4^-", "dihydrogen phosphate"}, {"H2PO3^-", "dihydrogen phosphite"}, {"H2PO2^-", "dihydrogen hypophosphite"},
			{"HCO4^-", "hydrogen percarbonate"}, {"HCO3^-", "hydrogen carbonate"}, {"HCO2^-", "hydrogen carbonite"}, {"HCO^-","hydrogen hypocarbonite"},
			{"HSO5^-", "hydrogen persulfate"}, {"HSO4^-", "hydrogen sulfate"}, {"HSO3^-","hydrogen sulfite"}, {"HSO2^-", "hydrogen hyposulfite"},
			{"ClO4^-", "perchlorate"}, {"ClO3^-", "chlorate"}, {"ClO2^-", "chlorite"}, {"ClO^-", "hypochlorite"},
			{"BrO4^-", "perbromate"}, {"BrO3^-", "bromate"}, {"BrO2^-", "bromite"}, {"BrO^-", "hypobromite"},
			{"IO4^-", "periodate"}, {"IO3^-", "iodate"}, {"IO2^-", "iodite"}, {"IO^-", "hypoiodite"},
			{"NO4^-", "pernitrate"}, {"NO3^-", "nitrate"}, {"NO2^-", "nitrite"}, {"NO^-", "hyponitrite"},
			{"MnO4^-", "permanganate"}, {"MnO3^-", "manganate"}, {"MnO2^-", "manganite"}, {"MnO^-", "hypomanganite"},
			{"CO4^2-", "percarbonate"}, {"CO3^2-", "carbonate"}, {"CO2^2-", "carbonite"}, {"CO^2-", "carbonite"},
			{"CrO5^2-", "perchromate"}, {"CrO4^2-", "chromate"}, {"CrO3^2-", "chromite"}, {"CrO2^2-", "hypochromite"},
			{"Cr2O8^2-", "perdichromate"}, {"Cr2O7^2-", "dichromate"}, {"Cr2O6^2-", "dichromite"}, {"Cr2O5^2-", "hypodichromite"},
			{"HPO4^2-", "hydrogen perphosphate"}, {"HPO3^2-", "hydrogen phosphate"}, {"HPO2^2-", "hydrogen phosphite"}, {"HPO^2-", "hydrogen hypophosphite"}, 
			{"C2O5^2-", "peroxalate"}, {"C2O4^2-", "oxalate"}, {"C2O3^2-", "oxalite"}, {"C2O2^2-", "hypooxalite"},
			{"SiO4^2-", "persilicate"}, {"SiO3^2-", "silicate"}, {"SiO2^2-", "silicite"}, {"SiO^2-", "hyposilicite"},
			{"SO5^2-", "persulfate"}, {"SO4^2-", "sulfate"}, {"SO3^2-", "sulfite"}, {"SO2^2-", "hyposulfite"},
			{"S2O4^2-", "perthiosulfate"}, {"S2O3^2-", "thiosulfate"}, {"S2O2^2-", "thiosulfite"}, {"S2O^2-", "hypothiosulfite"},
			{"BO4^3-", "perborate"}, {"BO3^3-", "borate"}, {"BO2^3-", "borite"}, {"BO^3-", "hypoborite"},
			{"PO5^3-", "perphosphate"}, {"PO4^3-", "phosphate"}, {"PO3^3-", "phosphite"}, {"PO2^3-", "hypophosphite"},
			{"P3O11^5-", "pertripolyphosphate"}, {"P3O1O^5-", "tripolyphosphate"}, {"P3O9^5-", "tripolyphosphite"}, {"P3O8^5-", "hypotripolyphosphite"}};
	
	static String[][] specials = {{"C6H12O6", "glucose"}, {"C12H22O11", "sucrose"}, 
			{"I2", "iodine"}, {"Br2", "bromine"}, {"Cl2", "chlorine"}, {"F2", "flourine"},
			{"O2", "oxygen"}, {"N2", "nitrogen"}, {"P4", "phosphorus"}, {"S8", "sulfur"},
			{"O3", "ozone"}, {"NH4^+", "ammonium"}, {"NH3", "ammonia"}, {"Hg2^2+", "mercury(I)"}};
	
	static String[][] organicCompounds = {{"methane", "CH4"}, {"methene", "CH2"}, {"methanol", "CH3OH"},
			{"ethane", "C2H6"}, {"ethene", "C2H4"}, {"ethyne", "C2H2"}, {"ethanol", "C2H5OH"},
			{"propane", "C3H8"}, {"propene", "C3H6"}, {"propyne", "C3H4"}, {"propanol", "C3H7OH"},
			{"butane", "C4H10"}, {"butene", "C4H8"}, {"butyne", "C4H6"}, {"butanol", "C4H9OH"},
			{"pentane", "C5H12"}, {"pentene", "C5H10"}, {"pentyne", "C5H8"}, {"pentanol", "C5H11OH"},
			{"hexane", "C6H14"}, {"hexene", "C6H12"}, {"hexyne", "C6H10"}, {"hexanol", "C6H13OH"},
			{"heptane", "C7H16"}, {"heptene", "C7H14"}, {"heptyne", "C7H12"}, {"heptanol", "C7H15OH"},
			{"octane", "C8H18"}, {"octene", "C8H16"}, {"octyne", "C8H14"}, {"octanol", "C8H17OH"},
			{"nonane", "C9H20"}, {"nonene", "C9H18"}, {"nonyne", "C9H16"}, {"nonanol", "C9H19OH"},
			{"decane", "C10C22"}, {"decene", "C10H20"}, {"decyne", "C10H18"}, {"decanol", "C10H21OH"},
			// organic acids
			{"methanoic acid", "HCOOH(aq)"}, {"ethanoic acid", "CH3COOH(aq)"}, {"propanoic acid", "C2H5COOH(aq)"},
			{"butanoic acid", "C3H7COOH(aq)"}, {"pentanoic acid", "C4H9COOH(aq)"}, {"hexanoic acid", "C5H11COOH(aq)"},
			{"heptanoic acid", "C6H13COOH(aq)"}, {"octanoic acid", "C7H15COOH(aq)"}, {"nonanoic acid", "C8H17COOH(aq)"},
			{"decanoic acid", "C9H19COOH(aq)"}};
	
	static String[][] acidsIUPAC = {
			// ide endings
			{"H3N(aq)", "aqueous hydrogen nitride"}, {"HF(aq)", "aqueous hydrogen flouride"},
			{"H3P(aq)", "aqueous hydrogen phosphide"}, {"H2S(aq)", "aqueous hydrogen sulfide"},
			{"HCl(aq)", "aqeuous hydrogen chloride"}, {"H2Se(aq)", "aqueous hydrogen selenide"}, 
			{"HBr(aq)", "aqueous hydrogen bromide"}, {"H2Te(aq)", "aqueous hydrogen telluride"}, 
			{"HI(aq)", "aqueous hydrogen iodide"},
			// ate and ite endings 
			{"HC2H3O3(aq)", "aqueous hydrogen peracetate"}, {"HC2H3O2(aq)", "aqueous hydrogen acetate"}, {"HC2H3O(aq)", "aqueous hydrogen acetite"},
			{"HClO4(aq)", "aqueous hydrogen perchlorate"}, {"HClO3(aq)", "aqueous hydrogen chlorate"} , {"HClO2(aq)", "aqueous hydrogen chlorite"}, {"HClO(aq)", "aqueous hydrogen hypochlorite"},
			{"HBrO4(aq)", "aqueous hydrogen perbromate"}, {"HBrO3(aq)", "aqueous hydrogen bromate"}, {"HBrO2(aq)", "aqueous hydrogen bromite"}, {"HBrO(aq)", "aqueous hydrogen hypobromite"},
			{"HIO4(aq)", "aqueous hydrogen periodate"}, {"HIO3(aq)", "aqueous hydrogen iodate"}, {"HIO2(aq)", "aqueous hydrogen iodite"}, {"HIO(aq)", "aqueous hydrogen hypoiodite"},
			{"HNO4(aq)", "aqueous hydrogen pernitrate"}, {"HNO3(aq)", "aqueous hydrogen nitrate"}, {"HNO2(aq)", "aqueous hydrogen nitrite"}, {"HNO(aq)", "aqueous hydrogen hyponitrite"},
			{"HMnO4(aq)", "aqueous hydrogen permanganate"}, {"HMnO3(aq)", "aqueous hydrogen manganate"}, {"HMnO2(aq)", "aqueous hydrogen manganite"}, {"HMnO(aq)", "aqueous hydrogen hypomanganite"},
			{"H2CO4(aq)", "aqueous hydrogen percarbonate"}, {"H2CO3(aq)", "aqueous hydrogen carbonate"}, {"H2CO2(aq)", "aqueous hydrogen carbonite"}, {"HCO(aq)", "aqueous hydrogen hypocarbonite"},
			{"H2CrO5(aq)", "aqueous hydrogen perchromate"}, {"H2CrO4(aq)", "aqueous hydrogen chromate"}, {"H2CrO3(aq)", "aqueous hydrogen chromite"}, {"H2CrO2(aq)", "aqueous hydrogen hypochromite"},
			{"H2Cr2O8(aq)", "aqueous hydrogen perdichromate"}, {"H2Cr2O7(aq)", "aqueous hydrogen dichromate"}, {"H2Cr2O6(aq)", "aqueous hydrogen dichromite"}, {"H2Cr2O5(aq)", "aqueous hydrogen hypodichromite"},
			{"H2C2O5(aq)", "aqueous hydrogen peroxalate"}, {"H2C2O4(aq)", "aqueous hydrogen oxalate"}, {"H2C2O3(aq)", "aqueous hydrogen oxalite"}, {"H2C2O2(aq)", "aqueous hydrogen hypooxalite"},
			{"H2SiO4(aq)", "aqueous hydrogen persilicate"}, {"H2SiO3(aq)", "aqueous hydrogen silicate"}, {"H2SiO2(aq)", "aqueous hydrogen silicite"}, {"H2SiO(aq)", "aqueous hydrogen hyposilicite"},
			{"H2SO5(aq)", "aqueous hydrogen persulfate"}, {"H2SO4(aq)", "aqueous hydrogen sulfate"}, {"H2SO3(aq)", "aqueous hydrogen sulfite"}, {"H2SO2(aq)", "aqueous hydrogen hyposulfite"},
			{"H2S2O4(aq)", "aqueous hydrogen perthiosulfate"}, {"H2S2O3(aq)", "aqueous hydrogen thiosulfate"}, {"H2S2O2(aq)", "aqueous hydrogen thiosulfite"}, {"H2S2O(aq)", "aqueous hydrogen hypothiosulfite"},
			{"H3BO4(aq)", "aqueous hydrogen perborate"}, {"H3BO3(aq)", "aqueous hydrogen borate"}, {"H3BO2(aq)", "aqueous hydrogen borite"}, {"H3BO(aq)", "aqueous hydrogen hypoborite"},
			{"H3PO5(aq)", "aqueous hydrogen perphosphate"}, {"H3PO4(aq)", "aqueous hydrogen phosphate"}, {"H3PO3(aq)", "aqueous hydrogen phosphite"}, {"H3PO2(aq)", "aqueous hydrogen hypophosphite"},
			{"H5P3O11(aq)", "aqueous hydrogen pertripolyphosphate"}, {"H5P3O1O(aq)", "aqueous hydrogen tripolyphosphate"}, {"H5P3O9(aq)", "aqueous hydrogen tripolyphosphite"}, {"H5P3O8(aq)", "aqueous hydrogen hypotripolyphosphite"}
			};
	static String[][] acidsCLASSIC = {
			// ide endings
			{"H3N(aq)", "hydronitric acid"}, {"HF(aq)", "hydrofluoric acid"},
			{"H3P(aq)", "hydrophosphoric acid"}, {"H2S(aq)", "hydrosulfuric acid"},
			{"HCl(aq)", "hydrochloric acid"}, {"H2Se(aq)", "hydroselenic acid"}, 
			{"HBr(aq)", "hydrobromic acid"}, {"H2Te(aq)", "hydrotelluric acid"}, 
			{"HI(aq)", "hydroiodic acid"},
			// ate, ite alternating
			{"HC2H3O3(aq)", "peracetic acid"}, {"HC2H3O2(aq)", "acetic acid"}, 
				{"HC2H3O(aq)", "acetous acid"},
			{"HClO4(aq)", "perchloric acid"}, {"HClO3(aq)", "chloric acid"} , 
				{"HClO2(aq)", "chlorous acid"}, {"HClO(aq)", "hypochlorous acid"},
			{"HBrO4(aq)", "perbromic acid"}, {"HBrO3(aq)", "bromic acid"}, 
				{"HBrO2(aq)", "bromous acid"}, {"HBrO(aq)", "hypobromous acid"},
			{"HIO4(aq)", "periodic acid"}, {"HIO3(aq)", "iodic acid"}, 
				{"HIO2(aq)", "iodous acid"}, {"HIO(aq)", "hypoiodous acid"},
			{"HNO4(aq)", "pernitric acid"}, {"HNO3(aq)", "nitric acid"},
				{"HNO2(aq)", "nitrous acid"}, {"HNO(aq)", "hyponitrous acid"},
			{"HMnO4(aq)", "permanganic acid"}, {"HMnO3(aq)", "manganic acid"}, 
				{"HMnO2(aq)", "manganous acid"}, {"HMnO(aq)", "hypomanganous acid"},
			{"H2CO4(aq)", "percarbonic acid"}, {"H2CO3(aq)", "carbonic acid"}, 
				{"H2CO2(aq)", "carbonous acid"}, {"HCO(aq)", "hypocarbonous acid"},
			{"H2CrO5(aq)", "perchromic acid"}, {"H2CrO4(aq)", "chromic acid"}, 
				{"H2CrO3(aq)", "chromous acid"}, {"H2CrO2(aq)", "hypochromous acid"},
			{"H2Cr2O8(aq)", "perdichromic acid"}, {"H2Cr2O7(aq)", "dichromic acid"}, 
				{"H2Cr2O6(aq)", "dichromous acid"}, {"H2Cr2O5(aq)", "hypodichromous acid"},
			{"H2C2O5(aq)", "peroxalic acid"}, {"H2C2O4(aq)", "oxalic acid"}, 
				{"H2C2O3(aq)", "oxalous acid"}, {"H2C2O2(aq)", "hypooxalous acid"},
			{"H2SiO4(aq)", "persilicic acid"}, {"H2SiO3(aq)", "silicic acid"}, 
				{"H2SiO2(aq)", "silicous acid"}, {"H2SiO(aq)", "hyposilicous acid"},
			{"H2SO5(aq)", "persulfuric acid"}, {"H2SO4(aq)", "sulfuric acid"}, 
				{"H2SO3(aq)", "sulfurous acid"}, {"H2SO2(aq)", "hyposulfurous acid"},
			{"H2S2O4(aq)", "perthiosulfuric acid"}, {"H2S2O3(aq)", "thiosulfuric acid"}, 
				{"H2S2O2(aq)", "thiosulfurous acid"}, {"H2S2O(aq)", "hypothiosulfurous acid"},
			{"H3BO4(aq)", "perboric acid"}, {"H3BO3(aq)", "boric acid"}, 
				{"H3BO2(aq)", "borous acid"}, {"H3BO(aq)", "hypoborous acid"},
			{"H3PO5(aq)", "perphosphoric acid"}, {"H3PO4(aq)", "phosphoric acid"}, 
				{"H3PO3(aq)", "phosphorous acid"}, {"H3PO2(aq)", "hypophosphorous acid"},
			{"H5P3O11(aq)", "pertripolyphosphoric acid"}, {"H5P3O1O(aq)", "tripolyphosphoric acid"}, 
				{"H5P3O9(aq)", "tripolyphosphorous acid"}, {"H5P3O8(aq)", "hypotripolyphosphorous acid"}
			};
//	static String[][] ionicHydratesIUPAC = {{"ionName-#-water", ""}};
//	static String[][] ionicHydratesCOMM = {{"ionicCompound . #H2O", ""}};
	
	public static String[][] getOxyanions() {
		return oxyanions;
	}

	public static String[][] getSpecials() {
		return specials;
	}

	public static String[][] getOrgComps() {
		return organicCompounds;
	}

	public static String[][] getAcidsIUPAC() {
		return acidsIUPAC;
	}

	public static String[][] getAcidsClassic() {
		return acidsCLASSIC;
	}

//	public static String[][] getIHI() {
//		return ionicHydratesIUPAC;
//	}
//
//	public static String[][] getIHC() {
//		return ionicHydratesCOMM;
//	}
}
