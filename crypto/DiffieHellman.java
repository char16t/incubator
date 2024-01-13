import javax.crypto.KeyAgreement;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class DiffieHellman {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        new DiffieHellman().exec();
    }

    void exec() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        final Peer a = new Peer();
        final Peer b = new Peer();
        a.connect(b);
        System.out.println("Hello world!");
    }

    class Peer {

        private final PublicKey publicKey;
        private final PrivateKey privateKey;
        private byte[] sharedSecret;

        public Peer() throws NoSuchAlgorithmException {
            final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DiffieHellman");
            keyPairGenerator.initialize(2048, new SecureRandom());
            final KeyPair keyPair = keyPairGenerator.generateKeyPair();
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        }

        public PublicKey getPublicKey() {
            return publicKey;
        }

        public void connect(final Peer peer) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
            this.generateSecret(peer.getPublicKey());
            peer.generateSecret(this.getPublicKey());
        }

        private void generateSecret(final PublicKey key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
            final KeyAgreement keyAgreement = KeyAgreement.getInstance("DiffieHellman");
            keyAgreement.init(privateKey);

            final KeyFactory keyFactory = KeyFactory.getInstance("DiffieHellman");
            final X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key.getEncoded());
            final PublicKey receivedPublic = keyFactory.generatePublic(x509KeySpec);

            keyAgreement.doPhase(receivedPublic, true);

            this.sharedSecret = keyAgreement.generateSecret();
        }
    }
}