package proyecto;

import javax.naming.OperationNotSupportedException;

import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;

public class SparseMatrixCSC {

    private LoadFile loader = LoadFile.getInstance();
    @Setter
    private int[][] matrix;
    @Getter
    @Setter
    private int[] rows;
    @Getter
    @Setter
    private int[] columns;
    @Getter
    @Setter
    private int[] values;

    public void createRepresentation(String inputFile) throws OperationNotSupportedException, FileNotFoundException {
        //Load data
        loader.loadFile(inputFile);
        matrix = loader.getMatrix();
        calculateRowsAndColumns();
    }

    private void calculateRowsAndColumns() {
        int flatIndex = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    flatIndex++;
                }
            }
        }

        rows = new int[flatIndex];
        columns = new int[matrix[0].length + 1];
        values = new int[flatIndex];
        flatIndex = 0;

        for (int i = 0; i < matrix[0].length; i++) {
            columns[i] = flatIndex;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] != 0) {
                    rows[flatIndex] = j;
                    values[flatIndex] = matrix[j][i];
                    flatIndex++;
                }
            }
        }

        columns[matrix[0].length] = flatIndex;
    }

    public int getElement(int i, int j) throws OperationNotSupportedException {
        return matrix[i][j];
    }

    public int[] getRow(int i) throws OperationNotSupportedException {
        int[] row = new int[matrix[i].length];

        for (int j = 0; j < matrix[i].length; j++)
            row[j] = matrix[i][j];

        return row;
    }

    public int[] getColumn(int j) throws OperationNotSupportedException {
        int[] column = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                if (k == j) {
                    column[i] = matrix[i][k];
                }
            }
        }

        return column;
    }

    public void setValue(int i, int j, int value) throws OperationNotSupportedException {
        matrix[i][j] = value;
        calculateRowsAndColumns();
    }

    /*
     * This method returns a representation of the Squared matrix
     * @return object that contests the squared matrix;
     */
    public SparseMatrixCSC getSquareMatrix() throws OperationNotSupportedException {
        SparseMatrixCSC squaredMatrix = new SparseMatrixCSC();

        // Usar los metodos Set aqui de los atributos
        int[][] matrixPow = new int[matrix.length][matrix[1].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                matrixPow[i][k] = matrix[i][k] * matrix[i][k];
            }
        }

        squaredMatrix.setMatrix(matrixPow);
        squaredMatrix.calculateRowsAndColumns();

        return squaredMatrix;
    }

    /*
     * This method returns a representation of the transposed matrix
     * @return object that contests the transposed matrix;
     */
    public SparseMatrixCSC getTransposedMatrix() throws OperationNotSupportedException {
        SparseMatrixCSC squaredMatrix = new SparseMatrixCSC();

        // Usar los metodos Set aqui de los atributos
        int rows = matrix.length, colum = matrix[0].length;
        int[][] matrixTranspose = new int[colum][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colum; j++) {
                // Asigna M_transpose[j][i] como M[i][j]
                matrixTranspose[j][i] = matrix[i][j];
            }
        }

        squaredMatrix.setMatrix(matrixTranspose);
        squaredMatrix.calculateRowsAndColumns();

        return squaredMatrix;
    }
}