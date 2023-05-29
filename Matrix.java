import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * The Matrix Class provides the basic operations of numerical linear algebra. 
 * Various constructors create Matrices from two dimensional arrays of int numbers. Various "getters" and "setters" provide access to submatrices and matrix elements. 
 * The Matrix class represents a matrix with integer elements.
 * It provides methods to create and manipulate matrices.
 * Several methods implement basic matrix arithmetic, including matrix addition and multiplication, matrix norms, and element-by-element array operations. Methods for reading and printing matrices are also included.
 * @author Andrea Cotugno 4iB Marconi 
 */
public class Matrix {
    /**
     * Int matrix(int[][]), will contain the elements
     */
    private int [][] matrix;
    /**
     * Number of rows in the matrix
     */
    private int rows;
    /**
     * Number of cols in the matrix
     */
    private int cols;
    
    /**
     * Classic constructor, generate a number of rows, columns and column's elements randomly. Generate a number of rows and halve columns randomly from 0 to 100. 
     * While the value of the elements goes from 0 to 9999
     */
    public Matrix() {
        rows = 1 + (int)(Math.random() * 100);
        cols = 1 + (int)(Math.random() * 100);
        matrix = new int [rows][cols];
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = (int)(Math.random() * (9999 + 1));
            }
        }
    }

    /**
     * Constructs a Matrix with the specified number of rows and columns.
     * If the random parameter is set to true, the matrix will be filled with random values between 0 and 10000.
     * @param rows the number of rows in the matrix
     * @param cols the number of columns in the matrix
     * @param random a flag indicating whether the matrix should be filled with random values
     * @throws IllegalArgumentException if rows or cols is less than 0
    */
    public Matrix(int rows, int cols, boolean random) {
        if (rows < 0 || cols < 0) {
            throw new IllegalArgumentException("Illegal Rows or Cols Capacity");
        }
        this.rows = rows;
        this.cols = cols;
        matrix = new int [rows][cols];
        if (random) {
            Random rand = new Random();
            int[] values = rand.ints(rows * cols, 0, 10000).toArray();
            toMatrix(values);
        }
    }

    /**
     * Parameterized constructor, generate empty matrix
     * @param rows int : Number of rows
     * @param cols int : Number of cols
     * @throws IllegalArgumentException {@code if (rows < 0 && cols < 0)}
     */
    public Matrix(int rows, int cols) {
        this(rows, cols, false);
    }

    /**
     * Parameterized constructor, generate column numbers and column elements randomly
     * @param rows int : Number of rows
     * @throws IllegalArgumentException {@code if (rows < 0)}
     */
    public Matrix(int rows) {
        this(rows, (1 + (int)(Math.random() * 100)), true);
    }

    /**
     * Parametrized constructor, you pass the matrix, the number of rows and columns
     * @param matrix int[][] : 2D-Array
     * @param rows int : Number of rows
     * @param cols int : Number of cols
     * @throws IllegalArgumentException {@code if (rows < 0 && cols < 0)}
     */
    public Matrix(int[][] matrix, int rows, int cols) {
        if (rows < 0 && cols < 0)
            throw new IllegalArgumentException("Illegal Rows or Cols Capacity");
            
        this.rows = rows;
		this.cols = cols;
        this.matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, cols);
        }
    }

    /**
     * Parametrized constructor, you pass the matrix. The number of rows and columns is calculated dynamically
     */
    public Matrix(int[][] matrix) {
        this(matrix, matrix.length, matrix[0].length);
    }

    /**
     * Copies the values of a Matrix class object to the current object
     * @param matrixB : Matrix class object, copies its values to the current object
     */
    public Matrix(Matrix matrixB) {
        rows = matrixB.rows;
        cols = matrixB.cols;
        System.arraycopy(matrixB.matrix, 0, this.matrix, 0, rows * cols);
    }

    /**
     * This method checks if the second matrix has dimensions equal to the first
     * @param second_matrix int[][] : matrix with which to make the comparison
     * @return {@code true} if they have the same dimension, in the other {@code false} case
     */
    public boolean areEqualSize(int[][] second_matrix) {
        return (second_matrix.length == rows && second_matrix[0].length == cols) ? true : false;
    }

    /**
     * Find the smallest element in the matrix
     * @return int : minimum element
     */
    public int min() {
        int min = matrix[0][0];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (at(i, j) < min) {
                    min = at(i, j);
                }
            }
        }
        return min;
    }

    /**
     * Find the element with the largest value in the matrix
     * @return int : maximum element
     */
    public int max() {
        int max = matrix[0][0];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (at(i, j) > max) {
                    max = at(i, j);
                }
            }
        }
        return max;
    }

    /**
     * Calculate the sum between all the elements of the matrix
     * @return int : sum
     */
    public int sum() {
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += at(i, j);
            }
        }
        return sum;
    }

    /**
     * Calculate the average of all matrix elements
     * @return sum : average
     */
    public int average() {
        return sum() / (rows * cols);
    }

    /**
     * Providing rows and column indexes returns the corresponding item
     * @param row_index : int
     * @param cols_index : int
     * @return int : number
     */
    public int at(int row_index, int cols_index) {
        return matrix[row_index][cols_index];
    }

    /**
     * Change the rows's column {@code row_index}, in the array {@code numbers[]}.
     * Attention, if the array has a number of elements less than the number of elements present in the columns of the matrix, 
     * the matrix will adapt its columns to the size of the array, 
     * if the array has a greater number of elements of the columns of the matrix you will have an error
     * @param row_index : int, rows to be assigned
     * @param numbers : int[], array to be assigned to the rows
     * @throws IllegalArgumentException {@code if (row_index < 0 && row_index >= rows)}
     */
    public void set(int row_index, int[] numbers) {
        if (row_index < 0 && row_index >= rows) 
            throw new IllegalArgumentException("Illegal row size "+ row_index);

        System.arraycopy(numbers, 0, matrix[row_index], 0, cols);
        cols = numbers.length;
    }

    /**
     * Change the element at rows {@code row_index} and column {@code cols_index} in element {@code number}
     * @param row_index int : index of the rows
     * @param cols_index int : index of the cols
     * @param number int : number to set at {@code row_index, cols_index} 
     * @throws IllegalArgumentException {@code if (row_index < 0 && row_index >= rows || cols_index < 0 && cols_index >= cols)}
     */
    public void set(int row_index, int cols_index, int number) {
        if (row_index < 0 && row_index >= rows || cols_index < 0 && cols_index >= cols) 
            throw new IllegalArgumentException("Illegal row or cols size "+ row_index);
        matrix[row_index][cols_index] = number;
    }

    /**
     * Returns the first index match of the searched item in a int array with 2 element
     * You must also pass the rows and column number from which the search will start
     * @param row_start : int, line from which to start the search
     * @param cols_start : int, column from which to start the search
     * @param number : int, number to look for
     * @return int[] : Return double -1 if the searched number is not present in the matrix or the indexes value
     * @throws IllegalArgumentException {@code if (row_start >= rows && row_start < 0 || cols_start >= rows && cols_start < 0)}
     */
    public int[] indexesOf(int row_start, int cols_start, int number) {
        if (row_start >= rows && row_start < 0 || cols_start >= rows && cols_start < 0)
            throw new IllegalArgumentException("Illegal Row Or Col Start Size");

        for (int i = row_start; i < rows; i++) {
            for (int j = cols_start; j < cols; j++) {
                if (at(i, j) == number) {
                    int[] indexes = {i, j};
                    return indexes;
                }
            }
        }
        int[] error = {-1, -1};
        return error;
    }

    /**
     * Returns the first index match of the searched item in a int array with 2 element
     * @param number int : Number to be searched
     * @return int[] : Return double -1 if the searched number is not present in the matrix or the indexes value
     */
    public int[] indexesOf(int number) {
        int[] indexes = indexesOf(0, 0, number);
        return indexes;
    }

    /**
     * Counts the number of occurrences of a given element in the matrix.
     *
     * @param element The element to search for in the matrix.
     * @return The number of occurrences of the given element in the matrix.
     */
    public int countElementOccurrencesInMatrix(int element) {
        return (int) Arrays.stream(matrix).flatMapToInt(IntStream::of).filter(e -> e == element).count();
    }

    /**
     * Creates an empty matrix
     */
    public void emptyMatrix() {
        matrix = new int [rows][cols];
    }

    /**
     * Fill the matrix with only one number provided: {@code number}
     * @param number int number
     */
    public void fillMatrix(int number) {
        matrix = new int [rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                set(i, j, number);
            }
        }
    }

    /**
     * Fill the matrix with random values with preset rows and columns
     * @param max_value int : maximum randomly generated number
     */
    public void randomMatrixElement(int max_value) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                set(i, j, ((int)(Math.random() * (max_value + 1))));
            }
        }
    }

    /**
     * Fill a new matrix with random values, minimum number = 0; maximum number = {@code max_value}
     * @param rows int : Numbers of rows
     * @param cols int : Numbers of cols
     * @param max_value int : maximum randomly generated number
     * @return int[][] : {@code local_matrix} generated matrix
     * @throws IllegalArgumentException if{rows < 0 && cols < 0}
     */
    public static int[][] randomMatrix(int rows, int cols, int max_value) {
        if (rows < 0 && cols < 0) 
            throw new IllegalArgumentException("Illegal Rows or Cols size");

        int[][] local_matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                local_matrix[i][j] = ((int)(Math.random() * (max_value + 1)));
            }
        }
        return local_matrix;
    }

    /**
     * Add to the matrix a value for each column, you have to provide an array with a size equal to the number of rows in the matrix, 
     * if you provide a array with a larger size the other values will be discarded, 
     * in case of a smaller array size you will have an error
     * @param numbers
     */
    public void addToCols(int... numbers) {
        int[][] newMatrix = Arrays.copyOf(matrix, rows * (cols + 1));
        for (int i = 0; i < rows; i++) 
            newMatrix[i][cols] = numbers[i];
        
        matrix = Arrays.copyOf(newMatrix, rows * (cols + 1));
        cols++;
    }
    

    /**
     * Adds a rows to the matrix, requires a array as parameter
     * @param numbers : int[]
     * @throws IllegalArgumentException {@code if (row_index < 0 && row_index >= rows)}
     */
    public void addRow(int... numbers) {
        if (numbers.length != cols)
            throw new IllegalArgumentException("Illegal Array Lenght"); 

        int[][] newMatrix = new int[rows + 1][cols];
        System.arraycopy(matrix, 0, newMatrix, 0, matrix.length);
        newMatrix[rows] = numbers;

        matrix = Arrays.copyOf(newMatrix, (rows + 1) * cols);
        rows++;
    }

    /**
     * Sum each element of the matrix with each element of the second matrix
     * @param second_matrix : int[][], the second matrix must have the same size as the first
     * @return int[][] : Another matrix containing the results of the operation
     * @throws IllegalArgumentException <b>if</b> ({@code if the size of the second matrix is different from the matrix})
     */
    public int[][] matrixSum(int[][] second_matrix) {
        if (areEqualSize(second_matrix))
            throw new IllegalArgumentException("Illegal Second Matrix Capacity");
        
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, j, (at(i, j) + second_matrix[i][j]));
            }
        }
        return result.getMatrix();
    }
    
    /**
     * Multiply each element of the matrix by a second matrix provided
     * @param second_matrix : int[][]
     * @return int[][] : matrix with the product loaded
     * @throws IllegalArgumentException <b>if</b> ({@code second_matrix size is different to matrix size})
     */
    public int[][] simpleMatrixElementMultiplication(int[][] second_matrix) {
        if (areEqualSize(second_matrix))
            throw new IllegalArgumentException("Illegal Second Matrix Capacity");
            
            Matrix result = new Matrix(rows, cols);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    result.set(i, j, (at(i, j) * second_matrix[i][j]));
                }
            }
            return result.getMatrix();
    }
    
    /**
     * Returns the result of multiplying this matrix by the given matrix.
     * @param second_matrix : int[][]
     * @throws IllegalArgumentException if the number of columns in this matrix does not match the number of rows in the other matrix
     * @return int[][] : the result of multiplying this matrix by the given matrix
     */
    public int[][] matrixMultiplication(int[][] second_matrix) {
        if (cols != second_matrix[0].length)
            throw new IllegalArgumentException("The number of columns in this matrix must match the number of rows in the other matrix");
            
        int local_cols = second_matrix[0].length;
        int[][] local_matrix = new int[rows][local_cols];
        
        for(int i = 0; i < rows; i++){    
            for(int j = 0; j < local_cols; j++){        
                for(int k = 0; k < cols; k++){      
                    local_matrix[i][j] += at(i, k) * second_matrix[k][j];      
                }
            }
        }  
        return local_matrix;
    }

    /**
     * Returns the result of multiplying this matrix by the given matrix.
     * @param second_matrix : Matrix Object
     * @throws IllegalArgumentException if the number of columns in this matrix does not match the number of rows in the other matrix
     * @return Matrix Object : the result of multiplying this matrix by the given matrix
     */
    public Matrix matrixMultiplication(Matrix second_matrix) {
        int local_cols = second_matrix.getCols();
        if (cols != local_cols)
            throw new IllegalArgumentException("The number of columns in this matrix must match the number of rows in the other matrix");
            
        Matrix risult = new Matrix(rows, local_cols);
        
        for(int i = 0; i < rows; i++){    
            for(int j = 0; j < local_cols; j++){        
                for(int k = 0; k < cols; k++){      
                    risult.matrix[i][j] += at(i, k) * second_matrix.at(k, j);      
                }
            }
        }  
        return risult;
    }

    /**
     * Effect a scalar multiplication by a number
     * @param number : int number
     * @return : int[][] matrix with the results
     */
    public int[][] scalarMultiplication(int number) {
        int[][] local_matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                local_matrix[i][j] = at(i, j) * number;
            }
        }
        return local_matrix;
    }
    
    /**
     * Reverse the order of each rows
     * @return : int[][] matrix with the results
     */
    public int[][] flipMatrix() {
        int[][] flipped = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flipped[i][cols - j - 1] = at(i, j);
            }
        }
        return flipped;
    }

    /**
     * Rotate the matrix 90°, swapping the rows and columns
     * @return : int[][] matrix with the results
     */
    public int[][] transpose() {
        int[][] newMatrix = new int[cols][rows];
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newMatrix[j][i] = at(i, j);
            }
        }
        return newMatrix;
    }

    /**
     * Check if the matrix has no size
     * @return <b>true</b> or <b>false</b>
     */
    public boolean isEmpty() {
        return (rows == 0 || cols == 0) ? true : false;
    }

    /**
     * Converts the matrix to an array of integers.
     *
     * @return an array of integers containing the values of the matrix
     */
    public int[] toArray() {
        return Arrays.stream(matrix)
                     .flatMapToInt(IntStream::of)
                     .toArray();
    }

    /**
     * Copies the given array of numbers into the matrix.
     * @param numbers the array of numbers to be copied into the matrix
     * @throws IllegalArgumentException if the size of the given array does not match the size of the matrix
     */
    public void toMatrix(int... numbers) {
        if (numbers.length != rows * cols) {
            throw new IllegalArgumentException("Array size is illegal : "+ numbers.length);
        }
        for (int i = 0; i < rows; i++) {
            System.arraycopy(numbers, i * cols, matrix[i], 0, cols);
        }
    }
    
    /**
     * Returns the column of a specific rows in one array
     * @param row_index : int rows index
     * @return int[] : array contains the rows column
     */
    public int[] fromRowToArray(int row_index) {
        return matrix[row_index];
    }

    /**
     * Returns the size of the matrix
     * @return int[] : [0] == Sows; [1] == Cols
     */
    public int[] size() {
        return new int[] {getRows(), getCols()};
    }

    /**
     * Sorts the elements of the matrix in ascending order.
     */
    public void sort() {
        int[] local_array = toArray();
        Arrays.sort(local_array);
        toMatrix(local_array);
    }

    /**
     * Returns the first item in the first rows and column of the matrix
     * @return int : number
     */
    public int firstElement() {
        return at(0, 0);
    }

    /**
     * Returns the first element of a specific matrix rows
     * @return int : number
     */
    public int firstElementOfTheCols(int row) {
        return at(row, 0);
    }

    /**
     * Return last element of last rows of matrix
     * @return
     */
    public int lastElement() {
        return at(rows - 1, cols - 1);
    }

    /**
     * Returns the last element of a specific matrix rows
     * @return int : number
     */
    public int lastElementOfTheCols(int row) {
        return at(row, cols - 1);
    }
    
    /**
     * Standard method, returns the matrix
     * @return int[][] : The matrix
     */
    public int[][] getMatrix() {
        return this.matrix;
    }

    /**
     * Sets the matrix to the specified matrix.
     * @param matrix int[][] : the new matrix to set
     * @throws NullPointerException if the specified matrix is null
     * @throws IllegalArgumentException if the dimensions of the specified matrix do not match the dimensions of the current matrix
     */
    public void setMatrix(int[][] matrix) {
        if (matrix == null) 
            throw new NullPointerException("Matrix cannot be null");

        if (matrix.length != rows || matrix[0].length != cols) 
            throw new IllegalArgumentException("Invalid matrix dimensions");
        
        this.matrix = matrix;
    }
    

    /**
     * Standard method, returns the number of cols in the matrix
     * @return int : cols
     */
    public int getCols() {
        return this.cols;
    }

    /**
     * Standard method, set the cols of the matrix to another supplied and recreates the matrix
     * @param cols : int
    */
    public void setCols(int cols) {
        this.cols = cols;
        emptyMatrix();
    }

    /**
     * Standard method, returns the size of the rows in the matrix
     * @return int : rows
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * Standard method, set the size of the rows of the matrix to another supplied and recreates the matrix
     * @param rows
     */
    public void setRow(int rows) {
        this.rows = rows;
        emptyMatrix();
    }

    /**
     * Returns a string with all values of the matrix
     * @return String element : {@code string} 
     */
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append("[");
            for (int j = 0; j < cols; j++) {
                sb.append(matrix[i][j]).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append("]\n");
        }
        return sb.toString();
    }    
}