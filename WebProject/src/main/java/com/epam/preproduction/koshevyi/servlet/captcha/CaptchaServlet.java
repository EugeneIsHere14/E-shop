package com.epam.preproduction.koshevyi.servlet.captcha;

import com.epam.preproduction.koshevyi.captcha.entity.Captcha;
import com.epam.preproduction.koshevyi.captcha.strategy.CaptchaStrategy;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import static com.epam.preproduction.koshevyi.constant.Constants.CAPTCHA_STRATEGY_CONTEXT_ATTR;

/**
 * Servlet for drawing captcha.
 */
@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    private CaptchaStrategy captchaStrategy;

    @Override
    public void init(ServletConfig config) {
        captchaStrategy = (CaptchaStrategy) config.getServletContext().getAttribute(CAPTCHA_STRATEGY_CONTEXT_ATTR);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int width = 150;
        int height = 50;

        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = bufferedImage.createGraphics();

        Font font = new Font("Georgia", Font.BOLD, 22);
        graphics2D.setFont(font);

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        graphics2D.setRenderingHints(rh);

        GradientPaint gp = new GradientPaint(0, 0,
                Color.ORANGE, 0, height / 2, Color.CYAN, true);

        graphics2D.setPaint(gp);
        graphics2D.fillRect(0, 0, width, height);

        graphics2D.setColor(new Color(15, 10, 9));

        int x = 0;
        int y;

        Captcha captcha = captchaStrategy.getCaptcha(request);

        Random random = new Random();

        char[] chars = captcha.getValue().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            x += 20 + (Math.abs(random.nextInt()) % 15);
            y = 25 + Math.abs(random.nextInt()) % 20;
            graphics2D.drawChars(chars, i, 1, x, y);
        }

        graphics2D.dispose();

        response.setContentType("image/png");

        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        outputStream.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }
}