package com.manenkov.sandbox.iris;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;

import java.util.Map;
import java.util.Set;

public class JImpl {
    public long m(float a, float b, float c, float d) throws OrtException {
        var env = OrtEnvironment.getEnvironment();
        var session = env.createSession("rf_iris.onnx",new OrtSession.SessionOptions());
        var inputs = Map.of(
                "float_input",
                OnnxTensor.createTensor(env, new float[][] {new float[] { a, b, c, d }})
        );
        try (var results = session.run(inputs, Set.of("output_label"))) {
            return ((long[]) results.get("output_label").get().getValue())[0];
        }
    }
}
