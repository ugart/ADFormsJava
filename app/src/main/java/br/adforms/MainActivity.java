package br.adforms;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    String formatData = "NN/NN/NNNN";
    String formatCelular = "(NN)NNNNN-NNNN";

    protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    TextInputLayout campoCongregacaoLayout;
    EditText campoCongregacao;
    EditText campoNome;
    EditText campoIdade;
    EditText campoEmail;
    EditText campoTelefone;
    EditText campoNascimento;
    EditText campoBatismoAguas;
    EditText campoGraduacao;
    EditText campoPosGraduacao;
    EditText campoProfissao;
    EditText campoOutros;
    EditText campoInstrumento;

    Spinner bes;
    Spinner sexo;
    Spinner estadoCivil;
    Spinner escolaridade;
    Spinner trabalharCrianca;
    Spinner trabalharCircOracao;
    Spinner trabalharSecretaria;
    Spinner trabalharAssistSocial;
    Spinner trabalharComunicacao;
    Spinner trabalharCozinha;
    Spinner trabalharCantar;
    Spinner trabalharConstCivil;
    Spinner trabalharTrabAdolescente;
    Spinner trabalharEvanMissoes;
    Spinner trabalharTesouraria;
    Spinner trabalharEstBibLar;
    Spinner trabalharDecoracao;
    Spinner trabalharSeguranca;
    Spinner trabalharSom;
    Spinner trabalharTrabJovens;
    Spinner trabalharVisita;
    Spinner trabalharProfessorEBD;
    Spinner trabalharRecepcao;
    Spinner trabalharRegencia;
    Spinner trabalharZeladoria;
    Spinner trabalharInstrumento;

    Button buttonCadastroUsuario;

    ProgressBar progressBarCadastroUsuario;

    ConstraintLayout constraintLayoutMain;

    private String trabalharCriancaOpcaoEscolhida;
    private String trabalharCircOracaoOpcaoEscolhida;
    private String trabalharSecretariaOpcaoEscolhida;
    private String trabalharAssistSocialOpcaoEscolhida;
    private String trabalharComunicacaoOpcaoEscolhida;
    private String trabalharCozinhaOpcaoEscolhida;
    private String trabalharCantarOpcaoEscolhida;
    private String trabalharConstCivilOpcaoEscolhida;
    private String trabalharTrabAdolescenteOpcaoEscolhida;
    private String trabalharEvanMissoesOpcaoEscolhida;
    private String trabalharTesourariaOpcaoEscolhida;
    private String trabalharEstBibLarOpcaoEscolhida;
    private String trabalharDecoracaoOpcaoEscolhida;
    private String trabalharSegurancaOpcaoEscolhida;
    private String trabalharSomOpcaoEscolhida;
    private String trabalharTrabJovensOpcaoEscolhida;
    private String trabalharVisitaOpcaoEscolhida;
    private String trabalharProfessorEBDOpcaoEscolhida;
    private String trabalharRecepcaoOpcaoEscolhida;
    private String trabalharRegenciaOpcaoEscolhida;
    private String trabalharZeladoriaOpcaoEscolhida;
    private String trabalharInstrumentoOpcaoEscolhida;
    private String besOpcaoEscolhida;
    private String sexoOpcaoEscolhida;
    private String estadoCivilOpcaoEscolhida;
    private String escolaridadeOpcaoEscolhida;

    boolean besNaoSelecionado;
    boolean sexoNaoSelecionado;
    boolean estadoCivilNaoSelecionado;
    boolean escolaridadeNaoSelecionada;
    boolean trabalharCriancaNaoSelecionado;
    boolean trabalharCircOracaoNaoSelecionado;
    boolean trabalharSecretariaNaoSelecionado;
    boolean trabalharAssistSocialNaoSelecionado;
    boolean trabalharComunicacaoNaoSelecionado;
    boolean trabalharCozinhaNaoSelecionado;
    boolean trabalharCantarNaoSelecionado;
    boolean trabalharConstCivilNaoSelecionado;
    boolean trabalharTrabAdolescenteNaoSelecionado;
    boolean trabalharEvanMissoesNaoSelecionado;
    boolean trabalharTesourariaNaoSelecionado;
    boolean trabalharEstBibLarNaoSelecionado;
    boolean trabalharDecoracaoNaoSelecionado;
    boolean trabalharSegurancaNaoSelecionado;
    boolean trabalharSomNaoSelecionado;
    boolean trabalharTrabJovensNaoSelecionado;
    boolean trabalharVisitaNaoSelecionado;
    boolean trabalharProfessorEBDNaoSelecionado;
    boolean trabalharRecepcaoNaoSelecionado;
    boolean trabalharRegenciaNaoSelecionado;
    boolean trabalharZeladoriaNaoSelecionado;
    boolean trabalharInstrumentoNaoSelecionado;

    Calendar myCalendar = Calendar.getInstance();

    public static final Pattern emailPattern =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    String[] arrayBES = {"Selecione uma opção abaixo", "Sim", "Não"};
    String[] arraySexo = {"Selecione um sexo", "Masculino", "Feminino"};
    String[] arrayEstadoCivil = {"Selecione um estado civil", "Solteiro(a)", "Casado(a)", "Viúvo(a)", "Divorciado(a)"};
    String[] arrayEscolaridade = {"Selecione um nível de escolaridade", "Não alfabetizado", "Fundamental", "Médio", "Superior", "Teologia"};
    String[] arrayOpcoes = {"Selecione uma opção abaixo", "Gostaria muito", "Faria, se necessário fosse", "Não gostaria"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();

    }

    private void setupView() {

        constraintLayoutMain = findViewById(R.id.constraintLayoutMain);
        campoCongregacaoLayout = findViewById(R.id.campoCongregacaoLayout);
        campoCongregacao = findViewById(R.id.campoCongregacao);
        campoNome = findViewById(R.id.campoNome);
        campoIdade = findViewById(R.id.campoIdade);
        campoEmail = findViewById(R.id.campoEmail);
        campoTelefone = findViewById(R.id.campoTelefone);
        campoNascimento = findViewById(R.id.campoNascimento);
        campoBatismoAguas = findViewById(R.id.campoBatismoAguas);
        campoGraduacao = findViewById(R.id.campoGraduacao);
        campoPosGraduacao = findViewById(R.id.campoPosGraduacao);
        campoProfissao = findViewById(R.id.campoProfissao);
        campoOutros = findViewById(R.id.campoOutros);
        campoInstrumento = findViewById(R.id.campoInstrumento);
        sexo = findViewById(R.id.sexoSpinner);
        estadoCivil = findViewById(R.id.estadoCivilSpinner);
        escolaridade = findViewById(R.id.escolaridadeSpinner);
        bes = findViewById(R.id.besSpinner);
        buttonCadastroUsuario = findViewById(R.id.buttonCadastroUsuario);
        progressBarCadastroUsuario = findViewById(R.id.progressBarCadastroUsuario);

        trabalharCrianca = findViewById(R.id.trabCriancasSpinner);
        trabalharCircOracao = findViewById(R.id.trabCircOracaoSpinner);
        trabalharSecretaria = findViewById(R.id.secretariaSpinner);
        trabalharAssistSocial = findViewById(R.id.assistSocialSpinner);
        trabalharComunicacao = findViewById(R.id.comunicacaoSpinner);
        trabalharCozinha = findViewById(R.id.cozinhaSpinner);
        trabalharCantar = findViewById(R.id.cantarSpinner);
        trabalharConstCivil = findViewById(R.id.construcaoCivilSpinner);
        trabalharTrabAdolescente = findViewById(R.id.trabAdolescentesSpinner);
        trabalharEvanMissoes = findViewById(R.id.evangelismoMissoesSpinner);
        trabalharTesouraria = findViewById(R.id.tesourariaSpinner);
        trabalharEstBibLar = findViewById(R.id.estudoBiblicoLarSpinner);
        trabalharDecoracao = findViewById(R.id.decoracaoSpinner);
        trabalharSeguranca = findViewById(R.id.segurancaSpinner);
        trabalharSom = findViewById(R.id.sonoplastiaSomSpinner);
        trabalharTrabJovens = findViewById(R.id.trabJovensSpinner);
        trabalharVisita = findViewById(R.id.visitaSpinner);
        trabalharProfessorEBD = findViewById(R.id.professorEBDSpinner);
        trabalharRecepcao = findViewById(R.id.recepcaoSpinner);
        trabalharRegencia = findViewById(R.id.regenciaSpinner);
        trabalharZeladoria = findViewById(R.id.zeladoriaSpinner);
        trabalharInstrumento = findViewById(R.id.instrumentoSpinner);

        SimpleMaskFormatter smfDataNasc = new SimpleMaskFormatter(formatData);
        MaskTextWatcher maskDataNasc = new MaskTextWatcher(campoNascimento, smfDataNasc);
        campoNascimento.addTextChangedListener(maskDataNasc);

        SimpleMaskFormatter smfDataBatAguas = new SimpleMaskFormatter(formatData);
        MaskTextWatcher maskDataBatAguas = new MaskTextWatcher(campoBatismoAguas, smfDataBatAguas);
        campoBatismoAguas.addTextChangedListener(maskDataBatAguas);

        final DatePickerDialog.OnDateSetListener dateNascimento = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateNascimento(campoNascimento);
            }

        };

        final DatePickerDialog.OnDateSetListener dateBatismoAguas = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateNascimento(campoBatismoAguas);
            }

        };

        campoNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateNascimento, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.setCancelable(false);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        campoBatismoAguas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateBatismoAguas, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.setCancelable(false);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        maskFormatter();
        dropdownsAdapter();
        clickListener();

    }

    private void maskFormatter() {
        SimpleMaskFormatter smfCel = new SimpleMaskFormatter(formatCelular);
        MaskTextWatcher maskCelular = new MaskTextWatcher(campoTelefone, smfCel);
        campoTelefone.addTextChangedListener(maskCelular);
    }

    private void clickListener() {

        buttonCadastroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationCadastro();
            }
        });

    }

    private void dropdownsAdapter() {

        bes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayBES));
        bes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                besNaoSelecionado = position == 0;

                besOpcaoEscolhida = arrayBES[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                besNaoSelecionado = true;
            }
        });

        sexo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraySexo));
        sexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                sexoNaoSelecionado = position == 0;

                sexoOpcaoEscolhida = arraySexo[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sexoNaoSelecionado = true;
            }
        });

        estadoCivil.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayEstadoCivil));
        estadoCivil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                estadoCivilNaoSelecionado = position == 0;

                estadoCivilOpcaoEscolhida = arrayEstadoCivil[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                estadoCivilNaoSelecionado = true;
            }
        });

        escolaridade.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayEscolaridade));
        escolaridade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                escolaridadeNaoSelecionada = position == 0;

                escolaridadeOpcaoEscolhida = arrayEscolaridade[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                escolaridadeNaoSelecionada = true;
            }
        });

        trabalharCrianca.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharCrianca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharCriancaNaoSelecionado = position == 0;

                trabalharCriancaOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharCriancaNaoSelecionado = true;
            }
        });

        trabalharCircOracao.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharCircOracao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharCircOracaoNaoSelecionado = position == 0;

                trabalharCircOracaoOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharCircOracaoNaoSelecionado = true;
            }
        });

        trabalharSecretaria.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharSecretaria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharSecretariaNaoSelecionado = position == 0;

                trabalharSecretariaOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharSecretariaNaoSelecionado = true;
            }
        });

        trabalharAssistSocial.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharAssistSocial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharAssistSocialNaoSelecionado = position == 0;

                trabalharAssistSocialOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharAssistSocialNaoSelecionado = true;
            }
        });

        trabalharComunicacao.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharComunicacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharComunicacaoNaoSelecionado = position == 0;

                trabalharComunicacaoOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharComunicacaoNaoSelecionado = true;
            }
        });

        trabalharCozinha.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharCozinha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharCozinhaNaoSelecionado = position == 0;

                trabalharCozinhaOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharCozinhaNaoSelecionado = true;
            }
        });

        trabalharCantar.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharCantar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharCantarNaoSelecionado = position == 0;

                trabalharCantarOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharCantarNaoSelecionado = true;
            }
        });

        trabalharConstCivil.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharConstCivil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharConstCivilNaoSelecionado = position == 0;

                trabalharConstCivilOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharConstCivilNaoSelecionado = true;
            }
        });

        trabalharTrabJovens.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharTrabJovens.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharTrabJovensNaoSelecionado = position == 0;

                trabalharTrabJovensOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharTrabJovensNaoSelecionado = true;
            }
        });

        trabalharVisita.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharVisita.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharVisitaNaoSelecionado = position == 0;

                trabalharVisitaOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharVisitaNaoSelecionado = true;
            }
        });

        trabalharProfessorEBD.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharProfessorEBD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharProfessorEBDNaoSelecionado = position == 0;

                trabalharProfessorEBDOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharProfessorEBDNaoSelecionado = true;
            }
        });

        trabalharRecepcao.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharRecepcao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharRecepcaoNaoSelecionado = position == 0;

                trabalharRecepcaoOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharRecepcaoNaoSelecionado = true;
            }
        });

        trabalharRegencia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharRegencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharRegenciaNaoSelecionado = position == 0;

                trabalharRegenciaOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharRegenciaNaoSelecionado = true;
            }
        });

        trabalharZeladoria.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharZeladoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharZeladoriaNaoSelecionado = position == 0;

                trabalharZeladoriaOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharZeladoriaNaoSelecionado = true;
            }
        });

        trabalharTrabAdolescente.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharTrabAdolescente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharTrabAdolescenteNaoSelecionado = position == 0;

                trabalharTrabAdolescenteOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharTrabAdolescenteNaoSelecionado = true;
            }
        });

        trabalharEvanMissoes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharEvanMissoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharEvanMissoesNaoSelecionado = position == 0;

                trabalharEvanMissoesOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharEvanMissoesNaoSelecionado = true;
            }
        });

        trabalharTesouraria.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharTesouraria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharTesourariaNaoSelecionado = position == 0;

                trabalharTesourariaOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharTesourariaNaoSelecionado = true;
            }
        });

        trabalharEstBibLar.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharEstBibLar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharEstBibLarNaoSelecionado = position == 0;

                trabalharEstBibLarOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharEstBibLarNaoSelecionado = true;
            }
        });

        trabalharDecoracao.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharDecoracao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharDecoracaoNaoSelecionado = position == 0;

                trabalharDecoracaoOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharDecoracaoNaoSelecionado = true;
            }
        });

        trabalharSeguranca.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharSeguranca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharSegurancaNaoSelecionado = position == 0;

                trabalharSegurancaOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharSegurancaNaoSelecionado = true;
            }
        });

        trabalharSom.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharSom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharSomNaoSelecionado = position == 0;

                trabalharSomOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharSomNaoSelecionado = true;
            }
        });

        trabalharInstrumento.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayOpcoes));
        trabalharInstrumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                trabalharInstrumentoNaoSelecionado = position == 0;

                trabalharInstrumentoOpcaoEscolhida = arrayOpcoes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                trabalharInstrumentoNaoSelecionado = true;
            }
        });

        clickListener();

    }

    private boolean isPhoneValid(String telefone) {

        if (telefone.length() != 14) {
            return false;
        } else {

            try {

                int foneValue = (Integer.valueOf(telefone.substring(1, 3))
                        + Integer.valueOf(telefone.substring(4, 9))
                        + Integer.valueOf(telefone.substring(10, 14)));

                return foneValue != 0;

            } catch (Exception e) {
                return false;
            }

        }

    }

    public static boolean isEmailValid(String emailStr) {
        Matcher matcher = emailPattern.matcher(emailStr);
        return matcher.find();
    }

    public static boolean isDateValid(String data) {

        if (data.length() < 10) return false;

        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Long dtNascimento = null;

        try {
            dtNascimento = dateFormat.parse(data).getTime();
        } catch (ParseException ignored) {
        }

        Date date = new Date(dtNascimento);
        String dateTry = dateFormat.format(date);

        if (!dateTry.equals(data)) {
            return false;
        }

        if (dia == 00 || dia > 31)
            return false;

        if (mes == 0 || mes == 00 || mes > 12)
            return false;

        if (data.length() != 10)
            return false;

        return true;

    }

    private void validationCadastro() {

        String email = campoEmail.getText().toString().trim();
        String telefone = campoTelefone.getText().toString().trim();
        String dataNascimento = campoNascimento.getText().toString().trim();
        String dataBatismoAguas = campoBatismoAguas.getText().toString().trim();

        if (campoCongregacao.length() == 0) {
            campoCongregacao.setError("Você precisa informar sua congregação.");
            campoCongregacao.requestFocus();
            return;
        }

        if (campoNome.length() == 0) {
            campoNome.setError("Você precisa informar seu nome completo.");
            campoNome.requestFocus();
            return;
        }

        if (campoIdade.length() == 0) {
            campoIdade.setError("Você precisa informar sua idade.");
            campoIdade.requestFocus();
            return;
        }

        if (email.length() == 0) {
            campoEmail.setError("Você precisa informar seu email.");
            campoEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            campoEmail.setError("Por favor, digite um e-mail válido.");
            campoEmail.requestFocus();
            return;
        }

        if (!isEmailValid(email)) {
            campoEmail.setError("Por favor, digite um e-mail válido.");
            campoEmail.requestFocus();
            return;
        }

        if (telefone.length() == 0) {
            campoTelefone.setError("Você precisa informar seu telefone");
            campoTelefone.requestFocus();
            return;
        }

        if (!isPhoneValid(telefone)) {
            campoTelefone.setError("Por favor, digite um número de telefone válido.");
            campoTelefone.requestFocus();
            return;
        }

        if (dataNascimento.length() == 0) {
            campoNascimento.setError("Você precisa informar sua data de nascimento.");
            campoNascimento.requestFocus();
            return;
        }

        if (!isDateValid(dataNascimento)) {
            campoNascimento.setError("Digite uma data de nascimento válida.");
            campoNascimento.requestFocus();
            return;
        }

        if (dataBatismoAguas.length() > 0) {
            if (!isDateValid(dataBatismoAguas)) {
                campoBatismoAguas.setError("Digite uma data de Batismo em Águas válida.");
                campoBatismoAguas.requestFocus();
                return;
            }
        }

        if (besNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa informar se é batizado com o Espírito Santo ou não",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            bes.requestFocus();
            return;
        }

        if (sexoNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar seu sexo.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            sexo.requestFocus();
            return;
        }

        if (estadoCivilNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar seu estado civil.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            estadoCivil.requestFocus();
            return;
        }

        if (escolaridadeNaoSelecionada) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar sua escolaridade.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            escolaridade.requestFocus();
            return;
        }

        if (trabalharCriancaNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 1 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharCircOracaoNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 2 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharSecretariaNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 3 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharAssistSocialNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 4 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharComunicacaoNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 5 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharCozinhaNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 6 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharCantarNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 7 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharConstCivilNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 8 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharTrabJovensNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 9 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharVisitaNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 10 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharProfessorEBDNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 11 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharRecepcaoNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 12 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharRegenciaNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 13 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharZeladoriaNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 14 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharTrabAdolescenteNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 15 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharEvanMissoesNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 16 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharTesourariaNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 17 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharEstBibLarNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 18 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharDecoracaoNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 19 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharSegurancaNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 20 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharSomNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 21 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (trabalharInstrumentoNaoSelecionado) {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você precisa selecionar uma opção na pergunta 22 do questionário.",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
            snack.show();
            return;
        }

        if (Utils.isConnectedToInternet(this)) {
            login();
        } else {
            Snackbar snack = Snackbar.make(
                    constraintLayoutMain, "Você está sem conexão com a internet",
                    Snackbar.LENGTH_LONG
            );
            snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            snack.show();
        }

    }

    private void limparCadastro() {
        dropdownsAdapter();
        campoCongregacao.setText("");
        campoNome.setText("");
        campoIdade.setText("");
        campoEmail.setText("");
        campoTelefone.setText("");
        campoGraduacao.setText("");
        campoPosGraduacao.setText("");
        campoProfissao.setText("");
        campoBatismoAguas.setText("");
        campoNascimento.setText("");
        campoInstrumento.setText("");
        campoOutros.setText("");
        campoCongregacaoLayout.requestFocus();
    }

    public void login() {
        progressBarCadastroUsuario.setVisibility(VISIBLE);

        Usuario newUser = new Usuario();
        newUser.setCongregacao(campoCongregacao.getText().toString().trim());
        newUser.setNome(campoNome.getText().toString().trim());
        newUser.setIdade(campoIdade.getText().toString().trim());
        newUser.setEmail(campoEmail.getText().toString().trim());
        newUser.setTelefone(campoTelefone.getText().toString().trim());
        newUser.setBes(besOpcaoEscolhida);
        newUser.setSexo(sexoOpcaoEscolhida);
        newUser.setEstadoCivil(estadoCivilOpcaoEscolhida);
        newUser.setEscolaridade(escolaridadeOpcaoEscolhida);
        newUser.setGraduacao(campoGraduacao.getText().toString().trim());
        newUser.setPosGraduacao(campoPosGraduacao.getText().toString().trim());
        newUser.setProfissao(campoProfissao.getText().toString().trim());
        newUser.setDataBatismoAguas(campoBatismoAguas.getText().toString().trim());
        newUser.setDataNascimento(campoNascimento.getText().toString().trim());
        newUser.setTrabalharCrianca(trabalharCriancaOpcaoEscolhida);
        newUser.setTrabalharCircOracao(trabalharCircOracaoOpcaoEscolhida);
        newUser.setTrabalharSecretaria(trabalharSecretariaOpcaoEscolhida);
        newUser.setTrabalharAssistSocial(trabalharAssistSocialOpcaoEscolhida);
        newUser.setTrabalharComunicacao(trabalharComunicacaoOpcaoEscolhida);
        newUser.setTrabalharCozinha(trabalharCozinhaOpcaoEscolhida);
        newUser.setTrabalharCantar(trabalharCantarOpcaoEscolhida);
        newUser.setTrabalharConstCivil(trabalharConstCivilOpcaoEscolhida);
        newUser.setTrabalharTrabAdolescente(trabalharTrabAdolescenteOpcaoEscolhida);
        newUser.setTrabalharEvanMissoes(trabalharEvanMissoesOpcaoEscolhida);
        newUser.setTrabalharTesouraria(trabalharTesourariaOpcaoEscolhida);
        newUser.setTrabalharEstBibLar(trabalharEstBibLarOpcaoEscolhida);
        newUser.setTrabalharDecoracao(trabalharDecoracaoOpcaoEscolhida);
        newUser.setTrabalharSeguranca(trabalharSegurancaOpcaoEscolhida);
        newUser.setTrabalharSom(trabalharSomOpcaoEscolhida);
        newUser.setTrabalharTrabJovens(trabalharTrabJovensOpcaoEscolhida);
        newUser.setTrabalharVisita(trabalharVisitaOpcaoEscolhida);
        newUser.setTrabalharProfessorEBD(trabalharProfessorEBDOpcaoEscolhida);
        newUser.setTrabalharRecepcao(trabalharRecepcaoOpcaoEscolhida);
        newUser.setTrabalharRegencia(trabalharRegenciaOpcaoEscolhida);
        newUser.setTrabalharZeladoria(trabalharZeladoriaOpcaoEscolhida);
        newUser.setTrabalharInstrumento(trabalharInstrumentoOpcaoEscolhida);
        newUser.setTipoInstrumentoPreferencia(campoInstrumento.getText().toString().trim());
        newUser.setTrabalharOutros(campoOutros.getText().toString().trim());

        NetworkClient
                .getNetworkClient()
                .postCadastro(newUser)
                .enqueue(new Callback<Usuario>() {

                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        progressBarCadastroUsuario.setVisibility(GONE);

                        Usuario result = response.body();

                        if (result != null) {

                            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.Dialog);
                            builder.setTitle("Oba!");
                            builder.setMessage("Cadastro realizado com sucesso!");
                            builder.setCancelable(false);

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    limparCadastro();
                                }
                            });

                            builder.show();

                        } else {

                            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.DialogError);
                            builder.setMessage("Infelizmente, algo deu errado");
                            builder.setCancelable(false);

                            builder.setPositiveButton("Tentar novamente", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    validationCadastro();
                                }
                            });

                            builder.show();

                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        progressBarCadastroUsuario.setVisibility(GONE);

                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.DialogError);
                        builder.setTitle("Ops... houve algum erro");
                        builder.setMessage("Erro na comunicação com o servidor!");
                        builder.setCancelable(false);

                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        builder.setPositiveButton("Tentar novamente", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                validationCadastro();
                            }
                        });

                        builder.show();

                        try {
                            throw new InterruptedException("Erro na comunicação com o servidor!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    private void updateNascimento(EditText nascimento) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));

        nascimento.setText(sdf.format(myCalendar.getTime()));
    }

}

