using System;
using System.Collections.Generic;
using System.IO;
using System.Windows;

namespace Base_de_Conocimiento
{
    public partial class MainWindow : Window
    {

        #region constructor

        public MainWindow()
        {
            InitializeComponent();
            LeerAtomos();
            LeerReglas();
        }

        #endregion constructor
        
        #region Propiedades

        const string rutaAtomos = "..\\..\\ArchivosDeTexto\\atomos.txt";
        const string rutaReglas = "..\\..\\ArchivosDeTexto\\reglas.txt";
        
        #endregion Propiedades

        #region Eventos

        private void aprop_Click(object sender, RoutedEventArgs e)
        {
            AgregarProposicion nuevoAtomo = new AgregarProposicion();
            nuevoAtomo.Show();
        }

        private void areg_Click(object sender, RoutedEventArgs e)
        {
            AgregarRegla nuevaRegla = new AgregarRegla();
            nuevaRegla.Show();
        }
        private void BtnEnF_Click(object sender, RoutedEventArgs e)

        {
            EncadenamientoAdelante nuevoEnAdelante = new EncadenamientoAdelante();
            nuevoEnAdelante.Show();

        }

        private void BtnEnB_Click(object sender, RoutedEventArgs e)
        {
            EncadenamientoAtras nuevoEnAtras = new EncadenamientoAtras();
            nuevoEnAtras.Show();
        }

        private void Window_Activated(object sender, EventArgs e)
        {
            LeerAtomos();
            LeerReglas();
        }

        #endregion Eventos

        #region Metodos
        private void LeerAtomos()
        {
            if (propBox.Items.Count > 0)
                propBox.Items.Clear();
            StreamReader archivo = new StreamReader(rutaAtomos);
            string linea = archivo.ReadLine();
            while (linea != null)
            {
                propBox.Items.Add(linea);
                linea = archivo.ReadLine();
            }
            archivo.Close();
        }
        private void LeerReglas()
        {
            if (regBox.Items.Count > 0)
                regBox.Items.Clear();
            StreamReader archivo = new StreamReader(rutaReglas);
            string linea = archivo.ReadLine();
            while (linea != null)
            {
                regBox.Items.Add(linea);
                linea = archivo.ReadLine();
            }
            archivo.Close();
        }

        #endregion Metodos

        
    }
}


