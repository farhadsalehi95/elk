package org.elasticsearch.xpack.core;

import org.elasticsearch.common.io.*;
import java.net.*;
import org.elasticsearch.common.*;
import java.nio.file.*;
import java.io.*;
import java.util.jar.*;

public class XPackBuild
{
    public static final XPackBuild CURRENT;
    private String shortHash;
    private String date;
    
    @SuppressForbidden(reason = "looks up path of xpack.jar directly")
    static Path getElasticsearchCodebase() {
        final URL url = XPackBuild.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            return PathUtils.get(url.toURI());
        }
        catch (URISyntaxException bogus) {
            throw new RuntimeException(bogus);
        }
    }
    
    XPackBuild(final String shortHash, final String date) {
        this.shortHash = shortHash;
        this.date = date;
    }
    
    public String shortHash() {
        return this.shortHash;
    }
    
    public String date() {
        return this.date;
    }
    
    static {
        final Path path = getElasticsearchCodebase();
        String shortHash = null;
        String date = null;
        Label_0109: {
            /*
            if (path.toString().endsWith(".jar")) {
                try {
                    final JarInputStream jar = new JarInputStream(Files.newInputStream(path, new OpenOption[0]));
                    try {
                        final Manifest manifest = jar.getManifest();
                        shortHash = manifest.getMainAttributes().getValue("Change");
                        date = manifest.getMainAttributes().getValue("Build-Date");
                        jar.close();
                    }
                    catch (Throwable t) {
                        try {
                            jar.close();
                        }
                        catch (Throwable exception) {
                            t.addSuppressed(exception);
                        }
                        throw t;
                    }
                    break Label_0109;
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            */
            shortHash = "Unknown";
            date = "Unknown";
        }
        CURRENT = new XPackBuild(shortHash, date);
    }
}
