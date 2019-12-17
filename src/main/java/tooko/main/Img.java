package tooko.main;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.RandomUtil;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/*
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.CategoryDataset;
*/

public class Img {

    public final int width;
    public final int height;
    private BufferedImage image;
    private Graphics2D graphics;

    public Img(int width, int height) {

        this(width, height, null);

    }

    public Img(int width, int height, Color backgroundColor) {

        this.width = width;
        this.height = height;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        graphics = image.createGraphics();

        if (backgroundColor != null) {

            graphics.setBackground(backgroundColor);
            graphics.clearRect(0, 0, width, height);

        } else {

            image = graphics.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            graphics.dispose();
            graphics = image.createGraphics();

        }

        // graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(Color.BLACK);
        graphics.setPaint(Color.BLACK);

    }
	
	/*

	public Img drawLineChart(String title,String cName,String vName,CategoryDataset dataset) {

		drawLineChart(title,cName,vName,dataset,0,0,width,height);

		return this;

	}


	public Img drawLineChart(String title,String cName,String vName,CategoryDataset dataset,int x,int y,int w,int h) {

		Paint paint = graphics.getPaint();
		Stroke stroke = graphics.getStroke();
		Font font = graphics.getFont();

		// graphics.setColor(MaterialColor.INDIGO.colorPrimaryDark);

		apply(ChartFactory
			  .createLineChart("",cName,vName,dataset))
			.draw(graphics,new Rectangle2D.Float(x,y,w,h));

		graphics.setPaint(paint);
		graphics.setStroke(stroke);
		graphics.setFont(font);

		fontSize(45);

		graphics.setColor(MaterialColor.INDIGO.colorAccent);

		drawTextCenter(0,0,0,height - 200,title);

		graphics.setFont(font);
		graphics.setPaint(paint);

		return this;

	}

	static {

		ChartFactory.setChartTheme(new StandardChartTheme("NTT") {{

					Paint[] paints = new Paint[MaterialColor.all.length];

					for (int index = 0;index < paints.length;index ++) {

						paints[index] = MaterialColor.all[index].colorPrimaryDark;

					}

					DefaultDrawingSupplier drawingSupplier = new DefaultDrawingSupplier(paints,paints,paints,
					DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,DefaultDrawingSupplier
					.DEFAULT_OUTLINE_STROKE_SEQUENCE,DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE);
					setDrawingSupplier(drawingSupplier);

					setPlotBackgroundPaint(Color.WHITE);

					setBaselinePaint(Color.BLACK);
					setRangeGridlinePaint(Color.BLACK);
					setDomainGridlinePaint(Color.BLACK);
					setPlotOutlinePaint(Color.WHITE);

				}});

	}

	private JFreeChart apply(JFreeChart chart) {

		chart.setPadding(new RectangleInsets(150,50,40,40));

		chart.setBackgroundPaint(Color.WHITE);

		chart.getPlot().setBackgroundPaint(Color.WHITE);

		chart.setBorderStroke(new BasicStroke(3));

		return chart;

	}
	
	*/

    public Img drawLineInterfere(int count) {

        Paint paint = graphics.getPaint();
        Stroke stoke = graphics.getStroke();

        final ThreadLocalRandom random = RandomUtil.getRandom();

        for (int i = 0; i < count; i++) {

            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width / 8);
            int ye = ys + random.nextInt(height / 8);

            graphics.setColor(ImgUtil.randomColor(random));
            graphics.setStroke(new BasicStroke((float) RandomUtil.randomDouble(4)));

            graphics.drawLine(xs, ys, xe, ye);
        }

        graphics.setPaint(paint);
        graphics.setStroke(stoke);

        return this;

    }

    public Img drawCircleInterfere(int count) {

        Paint paint = graphics.getPaint();

        final ThreadLocalRandom random = RandomUtil.getRandom();

        for (int i = 0; i < count; i++) {
            graphics.setColor(ImgUtil.randomColor(random));
            graphics.drawOval(random.nextInt(width), random.nextInt(height), random.nextInt(height >> 1), random.nextInt(height >> 1));
        }

        graphics.setPaint(paint);

        return this;

    }

    public Img drawShearInterfere(int count, Color color) {

        Paint paint = graphics.getPaint();

        int period = RandomUtil.randomInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = RandomUtil.randomInt(2);

        for (int i = 0; i < height; i++) {

            double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);

            graphics.copyArea(0, i, width, 1, (int) d, 0);

            graphics.setColor(color);
            graphics.drawLine((int) d, i, 0, i);
            graphics.drawLine((int) d + width, i, width, i);
        }

        period = RandomUtil.randomInt(40) + 10; // 50;

        frames = 20;
        phase = 7;

        for (int i = 0; i < width; i++) {

            double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);

            graphics.copyArea(i, 0, 1, height, 0, (int) d);

            graphics.setColor(color);
            graphics.drawLine(i, (int) d, i, 0);
            graphics.drawLine(i, (int) d + height, i, height);

        }

        graphics.setPaint(paint);

        return this;

    }

    public Img fontSize(int size) {

        font(graphics.getFont().getFontName(), size);

        return this;

    }

    public Img font(String newFont) {

        font(newFont, graphics.getFont().getSize());

        return this;

    }

    public Img font(String newFont, int size) {

        graphics().setFont(new Font(newFont, graphics.getFont().getStyle(), size));

        return this;

    }

    public int stringWidth(String string) {

        return graphics.getFontMetrics().stringWidth(string);

    }

    public int stringHeight() {

        return graphics.getFontMetrics().getHeight();

    }

    public Img drawImageCenter(int xPedding, int yPedding, int xMargin, int yMargin, File newImage, int width, int height) {

        drawImageCenter(xPedding, yPedding, xMargin, yMargin, newImage, width, height, null);

        return this;

    }

    public Img drawImageCenter(int xPedding, int yPedding, int xMargin, int yMargin, File newImage, int width, int height, Color bgColor) {

        int realX = xPedding + ((this.width - xMargin - width) / 2);
        int realY = yPedding + ((this.height - yMargin - height) / 2);

        drawImage(realX, realY, newImage, width, height, bgColor);

        return this;

    }

    public Img drawImageCenter(int xPedding, int yPedding, int xMargin, int yMargin, BufferedImage newImage, int width, int height) {

        drawImageCenter(xPedding, yPedding, xMargin, yMargin, newImage, width, height, null);

        return this;

    }

    public Img drawImageCenter(int xPedding, int yPedding, int xMargin, int yMargin, BufferedImage newImage, int width, int height, Color bgColor) {

        int realX = xPedding + ((this.width - xMargin - width) / 2);
        int realY = yPedding + ((this.height - yMargin - height) / 2);

        drawImage(realX, realY, newImage, width, height, bgColor);

        return this;

    }

    public Img drawImage(int x, int y, File image, int width, int height) {

        drawImage(x, y, image, width, height, null);

        return this;

    }

    public Img drawImage(int x, int y, File image, int width, int height, Color bgCplor) {

        try {

            drawImage(x, y, Thumbnails.of(image).size(width, height).asBufferedImage(), width, height, bgCplor);

        } catch (IOException ignored) {
        }

        return this;

    }

    public Img drawImage(int x, int y, BufferedImage newImage, int width, int height) {

        drawImage(x, y, newImage, width, height, null);

        return this;

    }

    public Img drawImage(int x, int y, BufferedImage newImage, int width, int height, Color bgColor) {

        if (bgColor == null) {

            graphics.drawImage(newImage, x, y, width, height, null);

        } else {

            graphics.drawImage(newImage, x, y, width, height, bgColor, null);

        }

        return this;

    }

    public Img drawTextCenter(int xPedding, int yPedding, int xMargin, int yMargin, String text) {

        int realX = xPedding + ((width - xPedding - xMargin) / 2) - (stringWidth(text) / 2);
        int realY = yPedding + ((height - yPedding - yMargin) / 2);

        drawText(realX, realY, text);

        return this;

    }

    public Img drawRandomColorTextCenter(int xPedding, int yPedding, int xMargin, int yMargin, String text) {

        int realX = xPedding + ((width - xPedding - xMargin) / 2) - (stringWidth(text) / 2);
        int realY = yPedding + ((height - yPedding - yMargin) / 2);

        drawRandomColorText(realX, realY, text);

        return this;

    }

    public Img drawText(int x, int y, String text) {

        Paint paint = graphics.getPaint();

        graphics.setPaint(new Color(0, 0, 0, 64));

        graphics.drawString(text, x, y);

        graphics.setPaint(paint);

        graphics.drawString(text, x, y);

        return this;

    }

    public Img drawRandomColorText(int x, int y, String text) {

        final ThreadLocalRandom random = RandomUtil.getRandom();

        Paint paint = graphics.getPaint();

        int length = 0;

        for (int index = 0; index < text.length(); index++) {

            graphics.setPaint(new Color(0, 0, 0, 64));

            String word = text.subSequence(index, index + 1).toString();

            graphics.drawString(word, x + length, y);

            graphics.setPaint(ImgUtil.randomColor(random));

            graphics.drawString(word, x + length, y);

            length += stringWidth(word);

        }

        graphics.setPaint(paint);

        return this;

    }

    public byte[] getBytes() {

        return getBytes("png");

    }

    public byte[] getBytes(String format) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            Thumbnails.of(image).size(width, height).outputFormat(format).outputQuality(1f).toOutputStream(out);

        } catch (IOException ignored) {
        }

        return out.toByteArray();

    }

    public Img toFile(File outPut) {

        toFile(outPut, "png");

        return this;

    }

    public Img toFile(File outPut, String format) {

        try {

            Thumbnails.of(image).size(width, height).outputFormat(format).outputQuality(1f).toFile(outPut);

        } catch (IOException ignored) {
        }

        return this;

    }

    public BufferedImage image() {

        return image;

    }

    public Graphics2D graphics() {

        return graphics;

    }

}
