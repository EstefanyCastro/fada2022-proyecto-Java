package proyecto;

import javax.naming.OperationNotSupportedException;
import java.io.FileNotFoundException;

import lombok.Getter;
import lombok.Setter;

public class SparseMatrixCoordinateFormat {

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

    // Crear representacion: Pasa de una matriz a su representacion.
    public void createRepresentation(String inputFile) throws OperationNotSupportedException, FileNotFoundException {
        // Load data
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
        columns = new int[flatIndex];
        values = new int[flatIndex];
        flatIndex = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    rows[flatIndex] = i;
                    columns[flatIndex] = j;
                    values[flatIndex] = matrix[i][j];
                    flatIndex++;
                }
            }
        }
    }

    // Obtener elemento: Dada una representacion y una posicion i,j. Solo basta con imprimir el valor
    // deseado en pantalla. Considere la complejidad de lo que realiza.
    public int getElement(int i, int j) throws OperationNotSupportedException {
        // No usar this.matrix aqui.
        return matrix[i][j];
    }

    // Obtener fila: Dada una representacion y una fila deseada. Considere la complejidad de lo que
    // realiza.
    public int[] getRow(int i) throws OperationNotSupportedException {
        // No usar this.matrix aqui.
        int[] row = new int[matrix[i].length];

        for (int j = 0; j < matrix[i].length; j++)
            row[j] = matrix[i][j];

        return row;
    }

    // Obtener columna: Dada una presentacion y una columna deseada. Considere la complejidad de lo
    // que realiza.lo deseado en pantalla. Considere la complejidad de lo que realiza.
    public int[] getColumn(int j) throws OperationNotSupportedException {
        // No usar this.matrix aqui.
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

    // Modificar posicion: Dada una representacion, un posicion i,j y un elemento, debe modificarse
    // la representacion para ingresar este elemento.
    public void setValue(int i, int j, int value) throws OperationNotSupportedException {
        // Cambiar los atributos rows, cols, values y matrix aqui
        matrix[i][j] = value;
        calculateRowsAndColumns();
    }

    /*
     * This method returns a representation of the Squared matrix
     * @return object that contests the squared matrix;
     */

    // Elevar al cuadrado: Retorna la representacion de la matriz elevada al cuadrado. Considere la
    // complejidad de lo que realiza.
    public SparseMatrixCoordinateFormat getSquareMatrix() throws OperationNotSupportedException {
        SparseMatrixCoordinateFormat squaredMatrix = new SparseMatrixCoordinateFormat();

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

    // Matriz transpuesta: Dada una representacion que contiene la matriz traspuesta.
    public SparseMatrixCoordinateFormat getTransposedMatrix() throws OperationNotSupportedException {
        SparseMatrixCoordinateFormat squaredMatrix = new SparseMatrixCoordinateFormat();

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