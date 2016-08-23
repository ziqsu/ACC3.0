package com.ziqisu.acc30;

import java.util.ArrayList;

/**
 * Created by ziqisu on 7/20/16.
 */
class WekaClassifier {

    public static double classify(Object[] i)
            throws Exception {

        double p = Double.NaN;
        p = WekaClassifier.N45b54fdc0(i);
        return p;
    }
    static double N45b54fdc0(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() <= 2.6811733) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() > 2.6811733) {
            p = WekaClassifier.N33c9eabe1(i);
        }
        return p;
    }
    static double N33c9eabe1(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 4;
        } else if (((Double) i[5]).doubleValue() <= -3.287909) {
            p = WekaClassifier.N64c9eafc2(i);
        } else if (((Double) i[5]).doubleValue() > -3.287909) {
            p = WekaClassifier.N529656557(i);
        }
        return p;
    }
    static double N64c9eafc2(Object []i) {
        double p = Double.NaN;
        if (i[4] == null) {
            p = 4;
        } else if (((Double) i[4]).doubleValue() <= 0.9814318) {
            p = WekaClassifier.N38ad46b63(i);
        } else if (((Double) i[4]).doubleValue() > 0.9814318) {
            p = WekaClassifier.N2b69a9955(i);
        }
        return p;
    }
    static double N38ad46b63(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 4;
        } else if (((Double) i[0]).doubleValue() <= 6.131322) {
            p = WekaClassifier.N570935d04(i);
        } else if (((Double) i[0]).doubleValue() > 6.131322) {
            p = 4;
        }
        return p;
    }
    static double N570935d04(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 3;
        } else if (((Double) i[6]).doubleValue() <= 593.6773) {
            p = 3;
        } else if (((Double) i[6]).doubleValue() > 593.6773) {
            p = 4;
        }
        return p;
    }
    static double N2b69a9955(Object []i) {
        double p = Double.NaN;
        if (i[3] == null) {
            p = 3;
        } else if (((Double) i[3]).doubleValue() <= -0.34898138) {
            p = 3;
        } else if (((Double) i[3]).doubleValue() > -0.34898138) {
            p = WekaClassifier.Ndc380e6(i);
        }
        return p;
    }
    static double Ndc380e6(Object []i) {
        double p = Double.NaN;
        if (i[11] == null) {
            p = 4;
        } else if (((Double) i[11]).doubleValue() <= 549.9013) {
            p = 4;
        } else if (((Double) i[11]).doubleValue() > 549.9013) {
            p = 3;
        }
        return p;
    }
    static double N529656557(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= 1.0570272) {
            p = WekaClassifier.N4caf5e628(i);
        } else if (((Double) i[0]).doubleValue() > 1.0570272) {
            p = 0;
        }
        return p;
    }
    static double N4caf5e628(Object []i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 2;
        } else if (((Double) i[10]).doubleValue() <= 9.126525) {
            p = WekaClassifier.N2aec73639(i);
        } else if (((Double) i[10]).doubleValue() > 9.126525) {
            p = WekaClassifier.N1641134214(i);
        }
        return p;
    }
    static double N2aec73639(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 2;
        } else if (((Double) i[0]).doubleValue() <= -1.369802) {
            p = WekaClassifier.N4b11227810(i);
        } else if (((Double) i[0]).doubleValue() > -1.369802) {
            p = WekaClassifier.N5401e95813(i);
        }
        return p;
    }
    static double N4b11227810(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() <= 1366.2767) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() > 1366.2767) {
            p = WekaClassifier.N2b55e1b111(i);
        }
        return p;
    }
    static double N2b55e1b111(Object []i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 2;
        } else if (((Double) i[8]).doubleValue() <= 0.30276483) {
            p = WekaClassifier.N1d3fe6f712(i);
        } else if (((Double) i[8]).doubleValue() > 0.30276483) {
            p = 1;
        }
        return p;
    }
    static double N1d3fe6f712(Object []i) {
        double p = Double.NaN;
        if (i[13] == null) {
            p = 2;
        } else if (((Double) i[13]).doubleValue() <= 0.3164014) {
            p = 2;
        } else if (((Double) i[13]).doubleValue() > 0.3164014) {
            p = 1;
        }
        return p;
    }
    static double N5401e95813(Object []i) {
        double p = Double.NaN;
        if (i[13] == null) {
            p = 1;
        } else if (((Double) i[13]).doubleValue() <= -0.13313445) {
            p = 1;
        } else if (((Double) i[13]).doubleValue() > -0.13313445) {
            p = 0;
        }
        return p;
    }
    static double N1641134214(Object []i) {
        double p = Double.NaN;
        if (i[12] == null) {
            p = 1;
        } else if (((Double) i[12]).doubleValue() <= 7.0785978155232385) {
            p = WekaClassifier.N121d305215(i);
        } else if (((Double) i[12]).doubleValue() > 7.0785978155232385) {
            p = WekaClassifier.N2be708c717(i);
        }
        return p;
    }
    static double N121d305215(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 1;
        } else if (((Double) i[7]).doubleValue() <= 7.061515514244959) {
            p = 1;
        } else if (((Double) i[7]).doubleValue() > 7.061515514244959) {
            p = WekaClassifier.N707774b816(i);
        }
        return p;
    }
    static double N707774b816(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() <= 7.204402) {
            p = 2;
        } else if (((Double) i[1]).doubleValue() > 7.204402) {
            p = 1;
        }
        return p;
    }
    static double N2be708c717(Object []i) {
        double p = Double.NaN;
        if (i[6] == null) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() <= 23.961346) {
            p = 2;
        } else if (((Double) i[6]).doubleValue() > 23.961346) {
            p = 1;
        }
        return p;
    }

}
