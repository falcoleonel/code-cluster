using System;
using System.IO;
using System.Windows;
using System.Text.RegularExpressions;
using System.Windows.Input;

namespace AtomosYReglas
{
    /// <summary>
    /// Interaction logic for Reglas.xaml
    /// </summary>
    public partial class Reglas : Window
    {
        #region Propiedades
        string rutaAtomos = ("..\\..\\ArchivosDeTexto\\Atomos.txt");
        string rutaReglas = ("..\\..\\ArchivosDeTexto\\Reglas.txt");
        int contPar = 0;
        #endregion
        #region Constructor
        public Reglas()
        {
            InitializeComponent();
            LeerAtomos();
            LeerReglas();
        }
        #endregion
        #region Eventos
        /// <summary>
        /// No permite ingreso de carácteres mediante botones
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void txtRegla_KeyDown(object sender, System.Windows.Input.KeyEventArgs e)
        {
            e.Handled = true;
        }
        /// <summary>
        /// Maneja el uso de los botones Back y Sup
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void txtRegla_PreviewKeyDown(object sender, System.Windows.Input.KeyEventArgs e)
       {
            string texto = txtRegla.Text;
            if (e.Key.Equals(Key.Back) || e.Key.Equals(Key.Delete)|| e.Key.Equals(Key.Space))
            {
                if(e.Key.Equals(Key.Delete))
                {
                    e.Handled = true;
                }
                else if(e.Key.Equals(Key.Space))
                {
                    e.Handled = true;
                }
                if (texto != "")
                {
                    if (texto[texto.Length - 1] == '>')
                    {
                        texto = texto.Remove(texto.Length - 2);
                        btnOr.IsEnabled = true;
                        btnImplica.IsEnabled = true;
                        btnAnd.IsEnabled = true;
                        btnParIzq.IsEnabled = true;
                    }
                    if(texto[texto.Length-1]=='(')
                    {
                        contPar--;
                        ValidarPar();
                    }
                    else if(texto[texto.Length - 1] == ')')
                    {
                        contPar++;
                        ValidarPar();
                    }
                    txtRegla.Text = texto.Trim();
                }
            }
        }
        /// <summary>
        /// Permite arrastrar items desde el listAtomos al txtRegla
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void listaAtomos_MouseUp(object sender, System.Windows.Input.MouseButtonEventArgs e)
        {
            if(!LetraAntes(txtRegla.Text))
                DragDrop.DoDragDrop(listaAtomos, listaAtomos.SelectedItem.ToString(), DragDropEffects.All);
        }
        private void btnAnd_Click(object sender, RoutedEventArgs e)
        {
            if(LetraAntes(txtRegla.Text))
                txtRegla.Text += btnAnd.Content.ToString();
        }

        private void btnOr_Click(object sender, RoutedEventArgs e)
        {
            if (LetraAntes(txtRegla.Text))
                txtRegla.Text += btnOr.Content.ToString();
        }
        private void btnNot_Click(object sender, RoutedEventArgs e)
        {
            string texto = txtRegla.Text;
            if (!LetraAntes(texto) && texto != "")
            {
                if (texto[texto.Length - 1] == '~')
                {
                    texto = texto.Remove(texto.Length - 1);
                    txtRegla.Text = texto;
                }
                else
                    txtRegla.Text += btnNot.Content.ToString();
            }
            else if (texto == "")
                txtRegla.Text += btnNot.Content.ToString();
        }
        private void btnParIzq_Click(object sender, RoutedEventArgs e)
        {
            string texto = txtRegla.Text;
            if(texto=="")
            {
                txtRegla.Text += btnParIzq.Content.ToString();
                contPar++;
            }
            else if (!LetraAntes(txtRegla.Text) && texto[texto.Length - 1] != '>')
            {
                txtRegla.Text += btnParIzq.Content.ToString();
                contPar++;
            }
            ValidarPar();
        }
        private void btnParDer_Click(object sender, RoutedEventArgs e)
        {
            string texto = txtRegla.Text;
            if (LetraAntes(txtRegla.Text))
            {
                txtRegla.Text += btnParDer.Content.ToString();
                contPar--;
            }
            ValidarPar();
        }
        private void btnImplica_Click(object sender, RoutedEventArgs e)
        {
            if (LetraAntes(txtRegla.Text)&&contPar==0)
            {
                txtRegla.Text += btnImplica.Content.ToString();
                btnOr.IsEnabled = false;
                btnAnd.IsEnabled = false;
                btnImplica.IsEnabled = false;
                btnParIzq.IsEnabled = false;
                btnParDer.IsEnabled = false;
            }
        }
        private void btnGuardar_Click(object sender, RoutedEventArgs e)
        {
            //si la regla está vacía se sale del método
            if (txtRegla.Text == "")
                return;
            //Dice que si el último carácter de la regla no es ^,~ y coincide con el Regex entonces separa la regla
            if (txtRegla.Text[txtRegla.Text.Length - 1] != '^' && txtRegla.Text[txtRegla.Text.Length - 1] != '~' && txtRegla.Text.Length != 0 && Regex.IsMatch(txtRegla.Text, "([~]*[a-uw-zA-Uw-z]+([\\^|v|~]+[a-uw-zA-Uw-z])*)+([-][\\>])+(([~]*[a-uw-zA-Uw-z])+([\\^|~]*[a-uw-zA-Uw-z]{1})*)+"))
                SepararRegla();
            else
                MessageBox.Show("No se ha ingresado regla a guardar o está mal escrita");
        }
        #endregion
        #region Métodos
        private void ValidarPar()
        {
            if (contPar == 0)
                btnParDer.IsEnabled = false;
            else
                btnParDer.IsEnabled = true;
        }
        private void LeerAtomos()
        {
            if (listaAtomos.Items.Count > 0)
                listaAtomos.Items.Clear();
            StreamReader archivo = new StreamReader(rutaAtomos);
            string linea = archivo.ReadLine();
            while (linea != null)
            {
                listaAtomos.Items.Add(linea);
                linea = archivo.ReadLine();
            }
            archivo.Close();
        }
        private void LeerReglas()
        {
            if (listaReglas.Items.Count > 0)
                listaReglas.Items.Clear();
            StreamReader archivo = new StreamReader(rutaReglas);
            string linea = archivo.ReadLine();
            while (linea != null)
            {
                listaReglas.Items.Add(linea);
                linea = archivo.ReadLine();
            }
            archivo.Close();
        }
        /// <summary>
        /// Mediante comparación REGEX dice si hay letra en la 
        /// última posición del texto
        /// </summary>
        /// <param name="texto"></param>
        /// <returns></returns>
        private bool LetraAntes(string texto)
        {
            bool hayLetra = false;
            if (texto.Length == 0)
                hayLetra = false;
            else if (Regex.IsMatch(texto[texto.Length - 1].ToString(), "[a-uw-zA-Uw-z]|\\)"))
            {
                hayLetra = true;
            }
            return hayLetra;
        }
        /// <summary>
        /// Guarda el átomo en el archivo
        /// </summary>
        private void Guardar(string[] regla)
        {
            /*Esto no está terminado llama a la clase Operaciones que está en otro archivo
            Operaciones op = new Operaciones();
            op.Separar(txtRegla.Text);
            */
            StreamWriter archivo = new StreamWriter(rutaReglas, true);
            for(int i=0;i<regla.Length;i++)
            {
                //Repetido te regresa true si está repetido en la lista de reglas
                if (!Repetido(regla[i]))
                    archivo.WriteLine(regla[i]);
                else
                    MessageBox.Show("La regla "+regla[i]+" está repetida","Regla repetida", MessageBoxButton.OK, MessageBoxImage.Exclamation);
            }
            archivo.Close();
            MessageBox.Show("Terminó proceso de guardado");//Se guardó exitosamente la regla");
            LeerReglas();
            txtRegla.Text = "";
            txtRegla.Focus();
            btnOr.IsEnabled = true;
            btnImplica.IsEnabled = true;
        }
        /// <summary>
        /// Valida que el átomo esté en la lista
        /// </summary>
        /// <returns></returns>
        private bool Repetido(string regla)
        {
            bool repetido = false;
            foreach (var item in listaReglas.Items)
            {
                if (item.Equals(txtRegla.Text))
                {
                    MessageBox.Show("La regla ya se encuentra en la lista");
                    repetido = true;
                }
            }
            return repetido;
        }
        private void ReglaValida()
        {
            bool valida = true;
            for(int i=0;i<listaReglas.Items.Count;i++)
            {
                foreach(var item in listaReglas.Items)
                {
                    string regla = item.ToString();
                    foreach(char letra in regla)
                    {
                        if (letra == 'v')
                            valida = false;
                    }
                }
                if(!valida)
                {

                }
            }
        }
        private void SepararRegla()
        {
            string reglaEntrada = txtRegla.Text;
            string antecedente="";
            string consecuencia= reglaEntrada.Substring(reglaEntrada.IndexOf('>')+1);
            string[] regla;
            int numReglas = 1;
            foreach(char v in reglaEntrada)
            {
                if (v == 'v')
                    numReglas++;
                if (v != '-')
                {
                    antecedente = antecedente + v;
                }
                else
                    break;
            }
            regla = new string[numReglas];
            int ultimov=0;
            //El metodo split viene en string(string.split(caracter que separa))
            regla = antecedente.Split('v');
            //este códio no es necesario, no recuerdo por que lo puse :v
            //for (int i = 0, j = 0; i < reglaEntrada.Length; i++)
            //{
            //    if (reglaEntrada[i] == 'v')
            //    {
            //        regla[j] = reglaEntrada.Substring(ultimov, i) + "->" + consecuencia;
            //        j++;
            //        ultimov = i;
            //    }
            //    else if (reglaEntrada[i] == '-')
            //    {
            //        regla[j] = reglaEntrada.Substring(ultimov + 1, 1) + "->" + consecuencia;
            //    }
            //    else if (j == 0)
            //        regla[j] = reglaEntrada;
            //}
            for (int i=0;i<regla.Length;i++)
            {
                regla[i] = regla[i] +"->"+ consecuencia;
            }
            Guardar(regla);
        }
        #endregion

    }
}
