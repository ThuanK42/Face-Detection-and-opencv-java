package Run;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfFloat;
import org.opencv.ml.ANN_MLP;
import org.opencv.ml.Ml;

public class ANN {
	final int MAX_DATA = 1000;
	ANN_MLP mlp;
	int input;
	int output;
	ArrayList<float[]> train;
	ArrayList<Float> label;
	MatOfFloat result;
	static String model2;

	public ANN(int i) {
		input = i;
		output = 1;
		mlp = ANN_MLP.create();
		MatOfInt m1 = new MatOfInt(input, input / 2, output);
		mlp.setLayerSizes(m1);
		mlp.setActivationFunction(ANN_MLP.SIGMOID_SYM);
		mlp.setTrainMethod(ANN_MLP.RPROP);
		result = new MatOfFloat();
		train = new ArrayList<float[]>();
		label = new ArrayList<Float>();
		model2 = "C:\\Users\\admin\\OneDrive\\Desktop\\AI_Test\\data\\test.xml";
	}

	void addData(float[] t, float l) {
		if (t.length != input)
			return;
		if (train.size() >= MAX_DATA)
			return;
		train.add(t);
		label.add(l);
	}

	int getCount() {
		return train.size();
	}

	void train() {
		float[][] tr = new float[train.size()][input];
		for (int i = 0; i < train.size(); i++) {
			for (int j = 0; j < train.get(i).length; j++) {
				tr[i][j] = train.get(i)[j];
			}
		}
		MatOfFloat response = new MatOfFloat();
		response.fromList(label);
		float[] trf = flatten(tr);
		Mat trainData = new Mat(train.size(), input, CvType.CV_32FC1);
		trainData.put(0, 0, trf);
		mlp.train(trainData, Ml.ROW_SAMPLE, response);
		trainData.release();
		response.release();
		train.clear();
		label.clear();
	}

	float predict(float[] i) {
		if (i.length != input)
			return -1;
		Mat test = new Mat(1, input, CvType.CV_32FC1);
		test.put(0, 0, i);
		float val = mlp.predict(test, result, 0);
		return val;
	}

	float[] getResult() {
		float[] r = result.toArray();
		return r;
	}

	float[] flatten(float[][] a) {
		if (a.length == 0)
			return new float[] {};
		int rCnt = a.length;
		int cCnt = a[0].length;
		float[] res = new float[rCnt * cCnt];
		int idx = 0;
		for (int r = 0; r < rCnt; r++) {
			for (int c = 0; c < cCnt; c++) {
				res[idx] = a[r][c];
				idx++;
			}
		}
		return res;
	}
}
