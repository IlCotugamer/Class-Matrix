# Class-Matrix
The Matrix Class implements numerical linear algebra operations with integer elements. It offers constructors to create Matrices from 2D arrays and "getters" and "setters" to access submatrices and elements. Basic matrix arithmetic, norms, and array operations are included, as well as methods for reading and printing matrices.

# Features
 - Create a matrix from a two-dimensional array of integers
 - Access submatrices and individual matrix elements with "getters" and "setters"
 - Perform basic matrix arithmetic, including addition and multiplication
 - Calculate matrix norms and perform element-by-element array operations
 - Read and print matrices with included methods

# Constructors
The Matrix Class offers several constructors to create a new matrix object.

# Default Constructor
The default constructor creates a new Matrix object with a random number of rows and columns, and fills the matrix with random values between 0 and 9999.

```java
Matrix matrix1 = new Matrix();
```

# Parameterized Constructors
The Matrix Class also offers several parameterized constructors with different arguments.

# Constructor with Rows and Columns
This constructor creates a new Matrix object with the specified number of rows and columns. If the random parameter is set to true, the matrix will be filled with random values between 0 and 10000.

```java
Matrix matrix2 = new Matrix(3, 4, true);
```

# Constructor with Rows
This constructor creates a new Matrix object with the specified number of rows and a random number of columns between 1 and 100. The matrix is filled with random values between 0 and 10000.

```java
Matrix matrix3 = new Matrix(5);
```

# Constructor with Matrix and Rows and Columns
This constructor creates a new Matrix object with the specified 2D array of integers, number of rows, and number of columns.

```java
int[][] myArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
Matrix matrix4 = new Matrix(myArray, 3, 3);
```

# Constructor with Matrix
This constructor creates a new Matrix object with the specified 2D array of integers. The number of rows and columns are calculated dynamically.

```java
int[][] myArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
Matrix matrix5 = new Matrix(myArray);
```

# Copy Constructor
This constructor creates a new Matrix object with the same values as the specified Matrix object.

```java
Matrix matrix6 = new Matrix(matrix5);
```

# Contributing
Contributions to the Matrix Class are welcome! If you have an idea for a new feature or improvement, feel free to fork the repository and submit a pull request.

# License

The Matrix Class is released under the MIT License. See the LICENSE file for details.

#  Contact
If you have any questions or suggestions regarding the Matrix Class, please contact me at [email address]. Thank you for using the Matrix Class!
