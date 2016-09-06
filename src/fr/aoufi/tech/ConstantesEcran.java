package fr.aoufi.tech;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

import fr.aoufi.vue.FrmListeTournois;

/**
 * Classe de centralisation des formats de police, de logo et de couleur des cellules.
 * Les différentes frame utilisant ces final beneficient des modifications de cette classe lorsque celle-ci est modifiée.
 *
 */

public interface ConstantesEcran {



	public final ImageIcon LOGO_GRAND      	= new ImageIcon(FrmListeTournois.class.getResource("/fr/aoufi/ressources/tournoisV.2.0 moyen.png"));
	public final ImageIcon LOGO_MOYEN       = new ImageIcon(FrmListeTournois.class.getResource("/fr/aoufi/ressources/tournoisV.2.0 moyen2.png"));
	public final Image LOGO_FAVICON       	= Toolkit.getDefaultToolkit().getImage(FrmListeTournois.class.getResource("/fr/aoufi/ressources/tournoisV.2.0 petit2.png"));


	public final Font FONT_TITRE1          	= new Font("Segoe UI", Font.PLAIN, 30);
	public final Font FONT_TITRE2 			= new Font("Segoe UI", Font.BOLD, 14); 
	public final Font FONT_BOUTON           = new Font("Segoe UI", Font.PLAIN, 14);
	public final Font FONT_GLOBAL           = new Font("Segoe UI", Font.PLAIN, 14);

	public final Color COLOR_TITRE1			= Color.WHITE;
	public final Color COLOR_TITRE2			= SystemColor.textHighlight;
	public final Color COLOR_LBL_CENTRALE	= Color.LIGHT_GRAY;


	public final Color COLOR_CELL			= Color.DARK_GRAY;
	public final Color COLOR_CELL_CENTRALE	= SystemColor.windowBorder;


	public final EtchedBorder BORDER_CENTRALE = new EtchedBorder(EtchedBorder.LOWERED, null, null);


}
