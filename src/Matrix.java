import java.util.Arrays;
import java.util.Random;

public class Matrix {

    private double[][] M;
    private int nrow;
    private int ncol;

    public Matrix(int nrow, int ncol) {
        M = new double[nrow][ncol];
        this.nrow = nrow;
        this.ncol = ncol;
    }

    public Matrix(double[][] N) {
        this.nrow = N.length;
        this.ncol = N[0].length;
        this.M = new double[nrow][ncol];
        for (int i = 0; i < nrow; i++) {
            System.arraycopy(N[i], 0, this.M[i], 0, ncol);
        }
    }

    public Matrix(double[] N, int axis) {
        if (axis==0) {
            this.nrow = N.length;
            this.ncol = 1;
            M = new double[nrow][ncol];
            for (int i = 0; i < nrow; i++) {
                M[i][0] = N[i];
            }
        } else if (axis==1) {
            this.nrow = 1;
            this.ncol = N.length;
            M = new double[nrow][ncol];
            System.arraycopy(N, 0, M[0], 0, ncol);
        }
    }

    public Matrix(int[][] N) {
        this.nrow = N.length;
        this.ncol = N[0].length;
        this.M = new double[this.nrow][this.ncol];
        for (int i = 0; i < this.nrow; i++) {
            for (int j = 0; j < this.ncol; j++) {
                this.M[i][j] = (double) N[i][j];
            }
        }
    }

    public Matrix ones(int nrow, int ncol) {
        Matrix result = new Matrix(nrow, ncol);
        result.fill(1);
        return result;
    }

    public Matrix zeros(int nrow, int ncol) {
        Matrix result = new Matrix(nrow, ncol);
        result.fill(0);
        return result;
    }

    public void fill(double value) {
        for (double[] doubles : M) {
            Arrays.fill(doubles, value);
        }
    }

    public int getNrow() {
        return this.nrow;
    }

    public int getNcol() {
        return this.ncol;
    }

    public double[][] getM() {
        return M;
    }

    public Matrix dot(Matrix N) throws Exception {
        int resNrow = this.nrow;
        int resNcol = N.getNcol();
        if (this.ncol != N.getNrow()) {
            throw new Exception("Dimension mismatch");
        }
        Matrix result = new Matrix(resNrow, resNcol);
        for (int i = 0; i < this.nrow; i++) {
            for (int j = 0; j < N.getNcol(); j++) {
                for (int k = 0; k < this.ncol; k++) {
                    result.getM()[i][j] += M[i][k] * N.getM()[k][j];
                }
            }
        }
        return result;
    }

    public void rand() {
        Random r = new Random();
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                M[i][j] = r.nextDouble();
            }
        }
    }

    public void add(double a) {
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                M[i][j] += a;
            }
        }
    }

    public void add(double[] a, int axis) throws Exception {
        if (axis==0) {
            // treat a as column
            if (a.length!=nrow) {
                throw new Exception("Dimension mismatch");
            }
            for (int i = 0; i < nrow; i++) {
                for (int j = 0; j < ncol; j++) {
                    M[i][j] += a[i];
                }
            }
        } else if (axis==1) {
            // treat a as row
            if (a.length!=ncol) {
                throw new Exception("Dimension mismatch");
            }
            for (int i = 0; i < nrow; i++) {
                for (int j = 0; j < ncol; j++) {
                    M[i][j] += a[j];
                }
            }
        }
    }

    public void add(Matrix N) throws Exception {
        if (N.getNrow() == nrow) {
            if (N.getNcol()==1) {
                for (int i = 0; i < nrow; i++) {
                    for (int j = 0; j < ncol; j++) {
                        M[i][j] += N.getM()[i][0];
                    }
                }
            } else if (N.getNcol() == ncol) {
                for (int i = 0; i < nrow; i++) {
                    for (int j = 0; j < ncol; j++) {
                        M[i][j] += N.getM()[i][j];
                    }
                }
            } else {
                throw new Exception("Dimension mismatch");
            }
        } else if (N.getNcol() == ncol) {
            if (N.getNrow()==1) {
                for (int i = 0; i < nrow; i++) {
                    for (int j = 0; j < ncol; j++) {
                        M[i][j] += N.getM()[0][1];
                    }
                }
            } else if (N.getNrow() == nrow) {
                for (int i = 0; i < nrow; i++) {
                    for (int j = 0; j < ncol; j++) {
                        M[i][j] += N.getM()[i][j];
                    }
                }
            } else {
                throw new Exception("Dimension mismatch");
            }
        } else {
            throw new Exception("Dimension mismatch");
        }
    }

    public void sub(Matrix N) throws Exception {
        if (N.getNrow() == nrow) {
            if (N.getNcol()==1) {
                for (int i = 0; i < nrow; i++) {
                    for (int j = 0; j < ncol; j++) {
                        M[i][j] -= N.getM()[i][0];
                    }
                }
            } else if (N.getNcol() == ncol) {
                for (int i = 0; i < nrow; i++) {
                    for (int j = 0; j < ncol; j++) {
                        M[i][j] -= N.getM()[i][j];
                    }
                }
            } else {
                throw new Exception("Dimension mismatch");
            }
        } else if (N.getNcol() == ncol) {
            if (N.getNrow()==1) {
                for (int i = 0; i < nrow; i++) {
                    for (int j = 0; j < ncol; j++) {
                        M[i][j] -= N.getM()[0][1];
                    }
                }
            } else if (N.getNrow() == nrow) {
                for (int i = 0; i < nrow; i++) {
                    for (int j = 0; j < ncol; j++) {
                        M[i][j] -= N.getM()[i][j];
                    }
                }
            } else {
                throw new Exception("Dimension mismatch");
            }
        } else {
            throw new Exception("Dimension mismatch");
        }
    }

    public void mul(double a) {
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                M[i][j] += a;
            }
        }
    }

    public Matrix t(Matrix N) {
        Matrix result = new Matrix(N.getNcol(), N.getNrow());
        for (int i = 0; i < N.getNrow(); i++) {
            for (int j = 0; j < N.getNcol(); j++) {
                result.getM()[j][i] = N.getM()[i][j];
            }
        }
        return result;
    }

    public Matrix copy() {
        Matrix result = new Matrix(nrow, ncol);
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                result.getM()[i][j] = M[i][j];
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (double[] doubles : M) {
            for (double aDouble : doubles) {
                result.append(aDouble).append(" ");
            }
            result.append('\n');
        }
        return result.toString();
    }

}
