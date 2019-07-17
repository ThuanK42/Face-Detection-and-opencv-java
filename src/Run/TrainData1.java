package Run;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.ml.Ml;
import org.opencv.ml.SVM;

public class TrainData1 {
	protected static final String PATH_POSITIVE = "C:\\Users\\admin\\OneDrive\\Desktop\\AI_Test\\data\\positivo";
	protected static final String PATH_NEGATIVE = "C:\\Users\\admin\\OneDrive\\Desktop\\AI_Test\\data\\negativo";
	protected static final String XML = "C:\\Users\\admin\\OneDrive\\Desktop\\AI_Test\\data\\test.xml";
	protected static final String FILE_TEST = "C:\\Users\\admin\\OneDrive\\Desktop\\AI_Test\\data\\negativo\\minh-hang-53375.jpg";

	private static Mat trainingImages;
	private static Mat trainingLabels;
	private static Mat trainingData;
	private static Mat classes;
	private static SVM clasificador;

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		trainingImages = new Mat();
		trainingLabels = new Mat();
		trainingData = new Mat();
		classes = new Mat();
	}

	public static void main(String[] args) {
		trainPositive();
		// trainNegative();
		train();
		test();
	}

	protected static void test() {
		// Mat in = HighGui.imread( new File( FILE_TEST ).getAbsolutePath(),
		// HighGui.CV_LOAD_IMAGE_GRAYSCALE );
		Mat in = Imgcodecs.imread(FILE_TEST);
		clasificador.load(XML);
		System.out.println(clasificador);
		Mat out = new Mat();
		in.convertTo(out, CvType.CV_32FC1);
		out = out.reshape(1, 1);
		System.out.println(out);
		System.out.println(clasificador.predict(out));
	}

	protected static void train() {
		trainingImages.copyTo(trainingData);
		trainingData.convertTo(trainingData, CvType.CV_32FC1);
		trainingLabels.copyTo(classes);
		classes.convertTo(classes, CvType.CV_32SC1);
		clasificador = SVM.create();
		clasificador.setKernel(SVM.LINEAR);
		clasificador.setType(SVM.C_SVC);
		clasificador.train(trainingData, SVM.LINEAR, classes);
		clasificador.save(XML);
	}

	protected static void trainPositive() {
		for (File file : new File(PATH_POSITIVE).listFiles()) {
			Mat img = getMat(file.getAbsolutePath());
			trainingImages.push_back(img.reshape(1, 1));
			trainingLabels.push_back(Mat.ones(new Size(1, 1), CvType.CV_32SC1));
		}
	}

	protected static void trainNegative() {
		for (File file : new File(PATH_NEGATIVE).listFiles()) {
			Mat img = getMat(file.getAbsolutePath());
			trainingImages.push_back(img.reshape(1, 1));
			trainingLabels.push_back(Mat.zeros(new Size(1,1), CvType.CV_32SC1));
			// trainingLabels.push_back(Mat.ones(new Size(1, 1), CvType.CV_32FC1));
		}
	}

	protected static Mat getMat(String path) {
		Mat img = new Mat();
		Mat con = Imgcodecs.imread(path);
		con.convertTo(img, CvType.CV_32FC1, 1.0 / 255.0);
		return img;
	}
}
