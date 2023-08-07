package org.launchcode.scorekeeperapp.controllers;

import com.google.zxing.WriterException;
import org.launchcode.scorekeeperapp.models.QRCodeGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("qrcode")
public class BarcodesController {
    //private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    @GetMapping("")
    public String getQRCode(Model model)
    {
        String medium="https://localhost:8080/qrcode";
        //String github="https://github.com/rahul26021999";

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(medium,250,250);

            // Generate and Save Qr Code Image in static/image folder
            //QRCodeGenerator.generateQRCodeImage(github,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("medium",medium);
        //model.addAttribute("github",github);
        model.addAttribute("qrcode",qrcode);

        return "qrcode";
        } //TODO - This can probably be deleted, its implemented in eventcontroller
}
