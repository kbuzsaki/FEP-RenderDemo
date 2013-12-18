
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.util.Vector;

/**
 * An implementation of the RenderedImage interface that delegates to an
 * internal BufferedImage. The purpose of this class is to provide read-only
 * access to such an image.
 * Using ImmutableImage prevents 
 */
public final class ImmutableImage implements RenderedImage, Transparency {

        // the private image that is delegated to and protected from writes
        private final BufferedImage image;
        
        /**
         * Package private constructor that uses whatever is passed as the
         * internal image
         */
        ImmutableImage(BufferedImage image) {
            this.image = image;
        }
        
        /** 
         * static factory that produces a copy of the image
         */
        public static ImmutableImage copyOf(BufferedImage source) {
            BufferedImage image = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
            
            // defensively copy the source image
            Graphics2D graphics = image.createGraphics();
            graphics.drawImage(source, null, 0, 0);
            graphics.dispose();
            
            return new ImmutableImage(image);
        }
        
        // all of the methods of RenderedImage and Transparency
        @Override
        public Vector<RenderedImage> getSources() {
            return image.getSources();
        }

        @Override
        public Object getProperty(String name) {
            return image.getProperty(name);
        }

        @Override
        public String[] getPropertyNames() {
            return image.getPropertyNames();
        }

        @Override
        public ColorModel getColorModel() {
            return image.getColorModel();
        }

        @Override
        public SampleModel getSampleModel() {
            return image.getSampleModel();
        }

        @Override
        public int getWidth() {
            return image.getWidth();
        }

        @Override
        public int getHeight() {
            return image.getHeight();
        }

        @Override
        public int getMinX() {
            return image.getMinX();
        }

        @Override
        public int getMinY() {
            return image.getMinY();
        }

        @Override
        public int getNumXTiles() {
            return image.getNumXTiles();
        }

        @Override
        public int getNumYTiles() {
            return image.getNumYTiles();
        }

        @Override
        public int getMinTileX() {
            return image.getMinTileX();
        }

        @Override
        public int getMinTileY() {
            return image.getMinTileY();
        }

        @Override
        public int getTileWidth() {
            return image.getTileWidth();
        }

        @Override
        public int getTileHeight() {
            return image.getTileHeight();
        }

        @Override
        public int getTileGridXOffset() {
            return image.getTileGridXOffset();
        }

        @Override
        public int getTileGridYOffset() {
            return image.getTileGridYOffset();
        }

        @Override
        public Raster getTile(int tileX, int tileY) {
            return image.getTile(tileX, tileY);
        }

        @Override
        public Raster getData() {
            return image.getData();
        }

        @Override
        public Raster getData(Rectangle rect) {
            return image.getData(rect);
        }

        @Override
        public WritableRaster copyData(WritableRaster raster) {
            return image.copyData(raster);
        }
        
        @Override
        public int getTransparency() {
            return image.getTransparency();
        }
        
    }