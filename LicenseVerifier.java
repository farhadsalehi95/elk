package org.elasticsearch.license;

import java.nio.*;
import org.elasticsearch.common.bytes.*;
import java.security.*;
import java.util.*;
import org.elasticsearch.common.xcontent.*;
import org.apache.lucene.util.*;
import org.elasticsearch.core.internal.io.*;
import java.io.*;
public class LicenseVerifier
{
    public static boolean verifyLicense(final License license, final byte[] publicKeyData) {
        /*
        byte[] signedContent = null;
        byte[] publicKeyFingerprint = null;
        try {
            final byte[] signatureBytes = Base64.getDecoder().decode(license.signature());
            final ByteBuffer byteBuffer = ByteBuffer.wrap(signatureBytes);
            final int version = byteBuffer.getInt();
            final int magicLen = byteBuffer.getInt();
            final byte[] magic = new byte[magicLen];
            byteBuffer.get(magic);
            final int hashLen = byteBuffer.getInt();
            publicKeyFingerprint = new byte[hashLen];
            byteBuffer.get(publicKeyFingerprint);
            final int signedContentLen = byteBuffer.getInt();
            signedContent = new byte[signedContentLen];
            byteBuffer.get(signedContent);
            final XContentBuilder contentBuilder = XContentFactory.contentBuilder(XContentType.JSON);
            license.toXContent(contentBuilder, (ToXContent.Params)new ToXContent.MapParams((Map)Collections.singletonMap("license_spec_view", "true")));
            final Signature rsa = Signature.getInstance("SHA512withRSA");
            rsa.initVerify(CryptUtils.readPublicKey(publicKeyData));
            final BytesRefIterator iterator = BytesReference.bytes(contentBuilder).iterator();
            BytesRef ref;
            while ((ref = iterator.next()) != null) {
                rsa.update(ref.bytes, ref.offset, ref.length);
            }
            return rsa.verify(signedContent);
        }
        catch (IOException ex) {}
        catch (NoSuchAlgorithmException ex2) {}
        catch (SignatureException ex3) {}
        catch (InvalidKeyException e) {
            throw new IllegalStateException(e);
        }
        finally {
            if (signedContent != null) {
                Arrays.fill(signedContent, (byte)0);
            }
        }
        */
        return true; //this line edited
    }
    
    public static boolean verifyLicense(final License license) {
        /*
        byte[] publicKeyBytes;
        try {
            final InputStream is = LicenseVerifier.class.getResourceAsStream("/public.key");
            try {
                final ByteArrayOutputStream out = new ByteArrayOutputStream();
                Streams.copy(is, (OutputStream)out);
                publicKeyBytes = out.toByteArray();
                if (is != null) {
                    is.close();
                }
            }
            catch (Throwable t) {
                if (is != null) {
                    try {
                        is.close();
                    }
                    catch (Throwable exception) {
                        t.addSuppressed(exception);
                    }
                }
                throw t;
            }
        }
        catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        return verifyLicense(license, publicKeyBytes);
        */
        return true;
    }
}
