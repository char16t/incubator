import java.security.*;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RSA256SignVerify {
    public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        String plainText = "Message to sign";

        // Sign
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(UTF_8));
        byte[] signatureBytes = privateSignature.sign();
        String signature = Base64.getEncoder().encodeToString(signatureBytes);

        // Verify
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(UTF_8));
        byte[] signatureBytes2 = Base64.getDecoder().decode(signature);
        boolean isValid = publicSignature.verify(signatureBytes);

        System.out.println("Message: " + plainText);
        System.out.println("Signature: " + signature);
        System.out.println("Valid: " + isValid);
    }
}
