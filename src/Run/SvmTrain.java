package Run;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.Ml;
import org.opencv.ml.SVM;
import org.opencv.ml.TrainData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.threshold;

public class SvmTrain {
	public static final String SVM_MODEL_FILE_PATH = "data/test.xml";

	public static final String SVM_ROOT = "data/right";

	// public static final String SVM_HAS_TEST = "has/test";
	public static final String SVM_HAS_TRAIN = "data/positivo";

	// public static final String SVM_NO_TEST = "no/test";
	public static final String SVM_NO_TRAIN = "data/negativo";

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		SvmTrain vl = new SvmTrain();
		vl.train();

	}

	void train() {
		// 初始化SVM
		SVM svm = SVM.create();
		svm.setType(SVM.C_SVC);
		svm.setKernel(SVM.RBF);
		svm.setDegree(0.1);
		// 1.4 bug fix: old 1.4 ver gamma is 1
		svm.setGamma(0.1);
		svm.setCoef0(0.1);
		svm.setC(1);
		svm.setNu(0.1);
		svm.setP(0.1);
		svm.setTermCriteria(new TermCriteria(1, 20000, 0.0001));

		// load data
		TrainData trainData = loadTrainData();

		// train
		long star = System.currentTimeMillis();
		System.out.println("start train...");
		svm.train(trainData.getSamples(),Ml.ROW_SAMPLE,trainData.getResponses());
		System.out.println("end train...total time ： " + (System.currentTimeMillis() - star) + "ms");

		svm.save(SVM_MODEL_FILE_PATH);
		System.out.println("save the train model...");

	}

	/**
	 * @return
	 */
	TrainData loadTrainData() {

		List<File> hasFileList = getFiles(SVM_HAS_TRAIN);
		List<File> noFileList = getFiles(SVM_NO_TRAIN);
		int hasCount = hasFileList.size();
		int noCount = noFileList.size();

		Mat samples = new Mat();
		Mat labels = new Mat();

		for (int i = 0; i < hasCount; i++) {// positive
			Mat img = getMat(hasFileList.get(i).getAbsolutePath());
			samples.push_back(img.reshape(1, 1));
			labels.push_back(Mat.ones(new Size(64, 128), CvType.CV_32FC1));
		}

		for (int j = 0; j < noCount; j++) {// negative
			Mat img = getMat(hasFileList.get(j).getAbsolutePath());
			samples.push_back(img.reshape(1, 1));
			labels.push_back(Mat.zeros(new Size(64, 128), CvType.CV_32SC1));
		}

		Mat trainingData = new Mat();
		Mat classes = new Mat();

		samples.copyTo(trainingData);
		trainingData.convertTo(trainingData, CvType.CV_32FC1);
		labels.copyTo(classes);
		return TrainData.create(trainingData, Ml.ROW_SAMPLE, classes);
	}

	public Mat getMat(String path) {
		Mat img = new Mat();
		Mat con = Imgcodecs.imread(path, Imgproc.COLOR_RGB2GRAY);
		con.convertTo(img, CvType.CV_32FC1, 1.0 / 255.0);
		return img;
	}

	public Mat getHistogramFeatures(Mat image) {
		Mat grayImage = new Mat();
		cvtColor(image, grayImage, Imgproc.COLOR_RGB2GRAY);

		Mat img_threshold = new Mat();
		threshold(grayImage, img_threshold, 0, 255, Imgproc.THRESH_OTSU + Imgproc.THRESH_BINARY);
		return img_threshold;
	}

	/**
	 * @Param floderPath
	 * @return
	 */
	public List<File> getFiles(String floderPath) {
		List<File> list = new ArrayList<File>();

		File file = new File(floderPath);

		if (!file.exists()) {
			System.out.println("Error : " + floderPath + " folder is not exist!");
			return list;
		}

		if (!file.isDirectory()) {
			System.out.println("Error : " + floderPath + "  is not a folder!");
			return list;
		}

		File[] files = file.listFiles();
		if (files.length == 0) {
			System.out.println("Error : " + floderPath + "  folder is empty!");
			return list;
		}

		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			list.add(f);
		}
		return list;
	}
}