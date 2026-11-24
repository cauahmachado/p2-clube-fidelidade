package projectsubscriptionloyaltyclub;

import projectsubscriptionloyaltyclub.visao.TelaClube;

import javax.swing.*;
public class TesteSubscriptionLoyaltClub {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                TelaClube tela = new TelaClube();
                tela.setVisible(true);
            });
        }
    }


