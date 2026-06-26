package com.domaco.venta_service.service;

import com.domaco.venta_service.model.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarBoleta(String destino, Venta venta) {

        try {

            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

            helper.setTo(destino);
            helper.setSubject("🧾 Boleta de compra - FERREMAS");

            String html = """
                    <h2>🧾 Boleta FERREMAS</h2>
                    <p>Cliente: %s</p>
                    <p>Producto: %s</p>
                    <p>Cantidad: %d</p>
                    <p>Total: $%.2f</p>
                    """.formatted(
                    venta.getCliente(),
                    venta.getProductoNombre(),
                    venta.getCantidad(),
                    venta.getTotal()
            );

            helper.setText(html, true);

            mailSender.send(mensaje);

        } catch (Exception e) {
            System.out.println("Error enviando correo: " + e.getMessage());
        }
    }
}