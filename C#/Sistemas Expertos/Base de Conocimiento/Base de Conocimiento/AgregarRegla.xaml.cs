using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Base_de_Conocimiento
{
    /// <summary>
    /// Interaction logic for AgregarRegla.xaml
    /// </summary>
    public partial class AgregarRegla : Window
    {
        #region Constructor
        public AgregarRegla()
        {
            InitializeComponent();
            LeerReglas();
            LeerAtomos();
        }
        #endregion Constructor

        #region Propiedades

        const string rutaAtomos = "..\\..\\ArchivosDeTexto\\atomos.txt";
        const string rutaReglas = "..\\..\\ArchivosDeTexto\\reglas.txt";

        #endregion Propiedades

        #region Eventos
        private void btnCan_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void btnlimpiar_Click(object sender, RoutedEventArgs e)
        {
            // limpiar cadena de la regla nueva y variables relacionadas
        }

        private void btnreg_Click(object sender, RoutedEventArgs e)
        {
            using (System.IO.StreamWriter file = new System.IO.StreamWriter(rutaReglas, true))

            {
                //file.WriteLine(regla);
            }
            Close();
        }
        private void Window_Activated(object sender, EventArgs e)
        {
            LeerReglas();
            LeerAtomos();
        }
        /// <summary>
        /// Bloquea ingreso de caracteres desde el teclado
        /// </summary>
        private void txtRegla_KeyDown(object sender, System.Windows.Input.KeyEventArgs e)
        {
            e.Handled = true;
        }
        private void txtRegla_PreviewKeyDown(object sender, System.Windows.Input.KeyEventArgs e)
        {

        }

        private void BtnAtomo_Click(object sender, RoutedEventArgs e)
        {
            if (!UltimoCaracterEsAtomo(txtRegla.Text))
            {
                txtRegla.Text += comboAnt.SelectedItem.ToString();
            }
        }
        #endregion Eventos

        #region Metodos
        private void LeerReglas()

        { 
            if (regbox.Items.Count > 0)
                regbox.Items.Clear();
            StreamReader archivo = new StreamReader(rutaReglas);
            string linea = archivo.ReadLine();
            while (linea != null)
            {
                regbox.Items.Add(linea);
                linea = archivo.ReadLine();
            }
            archivo.Close();
        }

        private bool UltimoCaracterEsAtomo(string texto)
        {
            bool esAtomo = false;
            if (texto.Length == 0)
                esAtomo = false;
            else if (Regex.IsMatch(texto[texto.Length - 1].ToString(), "[a-z]|\\)"))
            {
                esAtomo = true;
            }
            return esAtomo;
        }

        private void LeerAtomos()
        {
            if (comboAnt.Items.Count > 0)
                comboAnt.Items.Clear();
            StreamReader archivo = new StreamReader(rutaAtomos);
            string linea = archivo.ReadLine();
            while (linea != null)
            {
                comboAnt.Items.Add(linea);
                linea = archivo.ReadLine();
            }
            archivo.Close();
        }


        #endregion Metodos

        
    }
}
