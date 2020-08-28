package rzk.lib.util;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;

public class MathUtils
{
	// CONSTANTS

	public static final double GOLDEN_RATIO = 1.618033988749895;

	public static final double TWO_PI = 2 * Math.PI;

	// FUNCTIONS

	public static int sign(double x)
	{
		return x == 0 ? 0 : x < 0 ? -1 : 1;
	}

	public static boolean isEven(double x)
	{
		return x % 2 == 0;
	}

	public static boolean isOdd(double x)
	{
		return !isEven(x);
	}

	public static boolean isPositive(double x)
	{
		return x >= 0;
	}

	public static boolean isNegative(double x)
	{
		return !isPositive(x);
	}

	public static double sum(double... values)
	{
		double sum = 0;
		for (double value : values)
			sum += value;
		return sum;
	}

	public static double sum(DoubleUnaryOperator function, double... values)
	{
		double sum = 0;
		for (double value : values)
			sum += function.applyAsDouble(value);
		return sum;
	}

	public static double product(double... values)
	{
		double product = 1;
		for (double value : values)
		{
			if (value == 0) return 0;
			product *= value;
		}
		return product;
	}

	public static double product(DoubleUnaryOperator function, double... values)
	{
		double product = 1;
		for (double value : values)
		{
			if (value == 0) return 0;
			product *= function.applyAsDouble(value);
		}
		return product;
	}

	public static void square(double... values)
	{
		for (int i = 0; i < values.length; i++)
			values[i] *= values[i];
	}

	public static double[] squareNew(double... values)
	{
		double[] result = Arrays.copyOf(values, values.length);
		square(result);
		return result;
	}

	public static void power(double power, double... values)
	{
		for (int i = 0; i < values.length; i++)
			values[i] = Math.pow(values[i], power);
	}

	public static double[] powerNew(double power, double... values)
	{
		double[] result = Arrays.copyOf(values, values.length);
		power(power, result);
		return result;
	}

	public static void function(DoubleUnaryOperator function, double... values)
	{
		for (int i = 0; i < values.length; i++)
			values[i] = function.applyAsDouble(values[i]);
	}

	public static double[] functionNew(DoubleUnaryOperator function, double... values)
	{
		double[] result = Arrays.copyOf(values, values.length);
		function(function, result);
		return result;
	}

	public static double sigmoid(double x)
	{
		return 1 / (1 + Math.pow(Math.E, -x));
	}

	public static double sigmoidDerivative(double x)
	{
		double sig = sigmoid(x);
		return sig * (1 - sig);
	}

	public static double constrain(double input, double min, double max)
	{
		if (input < min) return min;
		return Math.min(input, max);
	}

	public static int constrain(int input, int min, int max)
	{
		if (input < min) return min;
		return Math.min(input, max);
	}

	public static double map(double input, double inMin, double inMax, double outMin, double outMax)
	{
		return outMin + (input - inMin) * (outMax - outMin) / (inMax - inMin);
	}

	public static double mapWithConstrain(double input, double inMin, double inMax, double outMin, double outMax)
	{
		return constrain(map(input, inMin, inMax, outMin, outMax), outMin, outMax);
	}

	// Vectors

	public static double[] add(double[] a, double value)
	{
		double[] result = new double[a.length];
		for (int i = 0; i < a.length; i++) result[i] = a[i] + value;
		return result;
	}

	public static double[] add(double[] a, double[] b)
	{
		if (a.length != b.length)
			throw new RuntimeException("Arrays have to be of the same size!");
		double[] result = new double[a.length];
		for (int i = 0; i < a.length; i++) result[i] = a[i] + b[i];
		return result;
	}

	public static double[] subtract(double[] a, double value)
	{
		double[] result = new double[a.length];
		for (int i = 0; i < a.length; i++) result[i] = a[i] - value;
		return result;
	}

	public static double[] subtract(double[] a, double[] b)
	{
		if (a.length != b.length)
			throw new RuntimeException("Arrays have to be of the same size!");
		double[] result = new double[a.length];
		for (int i = 0; i < a.length; i++) result[i] = a[i] - b[i];
		return result;
	}

	public static double[] multiply(double[] a, double value)
	{
		double[] result = new double[a.length];
		for (int i = 0; i < a.length; i++) result[i] = a[i] * value;
		return result;
	}

	public static double[] multiply(double[] a, double[] b)
	{
		if (a.length != b.length)
			throw new RuntimeException("Arrays have to be of the same size!");
		double[] result = new double[a.length];
		for (int i = 0; i < a.length; i++) result[i] = a[i] * b[i];
		return result;
	}

	public static double[] divide(double[] a, double value)
	{
		double[] result = new double[a.length];
		for (int i = 0; i < a.length; i++) result[i] = a[i] / value;
		return result;
	}

	public static double[] divide(double[] a, double[] b)
	{
		if (a.length != b.length)
			throw new RuntimeException("Arrays have to be of the same size!");
		double[] result = new double[a.length];
		for (int i = 0; i < a.length; i++) result[i] = a[i] / b[i];
		return result;
	}

	public static double dot(double[] a, double[] b)
	{
		if (a.length != b.length)
			throw new RuntimeException("Arrays have to be of the same size!");
		double sum = 0;
		for (int i = 0; i < a.length; i++) sum += a[i] * b[i];
		return sum;
	}

	// Matrices

	public static double[][] add(double[][] a, double value)
	{
		int rows = a.length;
		int columns = a[0].length;
		double[][] result = new double[rows][columns];
		for (int row = 0; row < rows; row++)
			for (int column = 0; column < columns; column++)
				result[row][column] = a[row][column] + value;
		return result;
	}

	public static double[][] add(double[][] a, double[][] b)
	{
		int rows = a.length;
		int columns = a[0].length;
		if (rows != b.length || columns != b[0].length)
			throw new RuntimeException("Matrices have to be of the same size!");
		double[][] result = new double[rows][columns];
		for (int row = 0; row < rows; row++)
			for (int column = 0; column < columns; column++)
				result[row][column] = a[row][column] + b[row][column];
		return result;
	}

	public static double[][] subtract(double[][] a, double value)
	{
		int rows = a.length;
		int columns = a[0].length;
		double[][] result = new double[rows][columns];
		for (int row = 0; row < rows; row++)
			for (int column = 0; column < columns; column++)
				result[row][column] = a[row][column] - value;
		return result;
	}

	public static double[][] subtract(double[][] a, double[][] b)
	{
		int rows = a.length;
		int columns = a[0].length;
		if (rows != b.length || columns != b[0].length)
			throw new RuntimeException("Matrices have to be of the same size!");
		double[][] result = new double[rows][columns];
		for (int row = 0; row < rows; row++)
			for (int column = 0; column < columns; column++)
				result[row][column] = a[row][column] - b[row][column];
		return result;
	}

	public static double[][] multiply(double[][] a, double value)
	{
		int rows = a.length;
		int columns = a[0].length;
		double[][] result = new double[rows][columns];
		for (int row = 0; row < rows; row++)
			for (int column = 0; column < columns; column++)
				result[row][column] = a[row][column] * value;
		return result;
	}

	public static double[][] multiply(double[][] matrixA, double[][] matrixB)
	{
		if (matrixA[0].length != matrixB.length)
			throw new RuntimeException("Columns of Matrix A have to match with rows of Matrix B");
		int rows = matrixA.length;
		int columns = matrixB[0].length;
		double[][] result = new double[rows][columns];
		double[][] temp = transpose(Arrays.copyOf(matrixB, matrixB.length));
		for (int row = 0; row < rows; row++)
			for (int column = 0; column < columns; column++)
				result[row][column] = dot(matrixA[row], temp[column]);
		return result;
	}

	public static double[][] hadamard(double[][] matrixA, double[][] matrixB)
	{
		int rows = matrixA.length;
		int columns = matrixA[0].length;
		if (rows != matrixB.length || columns != matrixB[0].length)
			throw new RuntimeException("Rows and columns of Matrix A have to match with rows and columns of Matrix B");
		double[][] result = new double[rows][columns];
		for (int row = 0; row < rows; row++)
			for (int column = 0; column < columns; column++)
				result[row][column] = matrixA[row][column] * matrixB[row][column];
		return result;
	}

	public static double[][] transpose(double[][] matrix)
	{
		int rows = matrix[0].length;
		int columns = matrix.length;
		double[][] result = new double[rows][columns];
		for (int row = 0; row < rows; row++)
			for (int column = 0; column < columns; column++)
				result[row][column] = matrix[column][row];
		return result;
	}

	public static double[][] upperTriangle(double[][] matrix)
	{
		int size = matrix.length;
		if (size != matrix[0].length)
			throw new RuntimeException("Matrix has to be a square Matrix");
		for (int i = 0; i < size - 1; i++)
		{
			for (int row = i + 1; row < size; row++)
			{
				double quotient = matrix[row][i] / matrix[i][i];
				matrix[row][i] = 0;

				for (int col = i + 1; col < size; col++)
					matrix[row][col] = matrix[row][col] - matrix[i][col] * quotient;
			}
		}
		return matrix;
	}

	//TODO
	public static double[][] lowerTriangle(double[][] matrix)
	{
		for (int i = 0; i < matrix.length - 1; i++)
		{
			for (int j = i + 1; j < matrix.length; j++)
			{
				double quotient = matrix[j][i] / matrix[i][i];

				matrix[j][i] = 0;

				for (int k = i + 1; k < matrix.length; k++)
					matrix[j][k] = matrix[j][k] - matrix[i][k] * quotient;
			}
		}
		return matrix;
	}

	public static double determinant(double[][] matrix)
	{
		double[][] triangle = upperTriangle(matrix);
		double sum = 1;
		for (int i = 0; i < matrix.length; i++)
		{
			if (triangle[i][i] == 0) return 0;
			sum *= triangle[i][i];
		}
		return sum;
	}

	public static double[][] copy(double[][] matrix)
	{
		double[][] copy = new double[matrix.length][matrix[0].length];
		for (int row = 0; row < matrix.length; row++)
			System.arraycopy(matrix[row], 0, copy[row], 0, matrix[0].length);
		return copy;
	}
}
