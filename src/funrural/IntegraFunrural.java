/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funrural;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import tools.ManipulaData;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import wsTerceiros.G5SeniorServices;
import wsTerceiros.ObjectFactory;
import wsTerceiros.TerceirosAquisicaoProducao3In;

/**
 *
 * @author luiz.barcellos
 */
public class IntegraFunrural {

    //private final ConexaoMySQL cn;
    private final DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");
    private final DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");
    private final DecimalFormat df = new DecimalFormat("#,##0.00");
    private String sql;
    private final ManipulaData mData = new ManipulaData();
    private String dti;
    private String dtf;

    private final int numEmp = 1;
    private Integer codFil;
    private String nomCnp;
    private String numNot;
    private String serNot;
    private String datEmi;
    private Double recBru;
    private Double valPre;
    private String tipOpe;
    private String cmpPrd;
    private Integer seqPrd;
    private final Integer indAqu = 1;
    private String numIns;

    public IntegraFunrural(String tipOpe, String competencia, int sequencia,
            int filial, String cpf, String cliente, String doc, String serie,
            String data, Double vlr_bruto, Double funrural, String senar) {

        this.tipOpe = tipOpe;
        this.cmpPrd = competencia;
        this.seqPrd = sequencia;
        this.codFil = filial;
        this.numIns = cpf;
        this.nomCnp = cliente;
        this.numNot = doc;
        this.serNot = serie;
        this.datEmi = data;
        this.recBru = vlr_bruto;
        this.valPre = funrural * 1.20 / 1.30;
        
        integracao();
        
    }

    private String escreveXML() {

        TerceirosAquisicaoProducao3In terc = new TerceirosAquisicaoProducao3In();
        ObjectFactory objectFactory = new ObjectFactory();
        final StringWriter out = new StringWriter();

        terc.setNumEmp(objectFactory.createTerceirosAquisicaoProducao3InNumEmp(numEmp));
        terc.setCodFil(objectFactory.createTerceirosAquisicaoProducao3InCodFil(codFil));
        terc.setCmpPrd(objectFactory.createTerceirosAquisicaoProducao3InCmpPrd(cmpPrd));
        terc.setSeqPrd(objectFactory.createTerceirosAquisicaoProducao3InSeqPrd(seqPrd));
        terc.setTipOpe(objectFactory.createTerceirosAquisicaoProducao3InTipOpe(tipOpe));
        terc.setIndAqu(objectFactory.createTerceirosAquisicaoProducao3InIndAqu(indAqu));
        terc.setNumIns(objectFactory.createTerceirosAquisicaoProducao3InNumIns(numIns));
        terc.setNomCnp(objectFactory.createTerceirosAquisicaoProducao3InNomCnp(nomCnp));
        terc.setNumNot(objectFactory.createTerceirosAquisicaoProducao3InNumNot(numNot));
        terc.setSerNot(objectFactory.createTerceirosAquisicaoProducao3InSerNot(serNot));
        terc.setDatEmi(objectFactory.createTerceirosAquisicaoProducao3InDatEmi(datEmi));
        terc.setRecBru(objectFactory.createTerceirosAquisicaoProducao3InRecBru(recBru));
        terc.setValPre(objectFactory.createTerceirosAquisicaoProducao3InValPre(valPre));

        JAXBContext context = null;

        try {
            context = JAXBContext.newInstance(terc.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(
                    javax.xml.bind.Marshaller.JAXB_FRAGMENT,
                    Boolean.TRUE
            );
            marshaller.marshal(terc, new StreamResult(out));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível escrever o XML: " + e);
        }

        return out.toString();

    }

    private boolean integracao() {
        boolean resposta = false;

        System.out.println("\nCriando objeto 'Service'.");
        G5SeniorServices service = new G5SeniorServices();
        System.out.println("\nObjeto 'Service' criado com sucesso.");

        QName portQName = new QName("http://services.senior.com.br", "rubi_Asynccom_senior_g5_rh_fp_terceirosPort");
        String req = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.senior.com.br\"><soapenv:Header/><soapenv:Body><ser:AquisicaoProducao_3><user>luiz.barcellos</user><password>Lu!z12345</password><encryption>0</encryption>"
                + escreveXML()
                + "</ser:AquisicaoProducao_3></soapenv:Body></soapenv:Envelope>";
        try { // Call Web Service Operation

            Dispatch<Source> sourceDispatch = null;
            sourceDispatch = service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);

            System.out.println("\nEnviando requisição: " + req);
            
            System.out.println("Resposta: " + sourceDispatch);
            
            
            Source result = sourceDispatch.invoke(new StreamSource(new StringReader(req)));
            System.out.println("Requisição Enviada: " + result);
            
            //LEITURA DA RESPOSTA DO XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult xmlOut = new StreamResult(new StringWriter());
            transformer.transform(result, xmlOut);
            System.out.println("Resposta: " + xmlOut.getWriter().toString());

            
            //CAPTURA DO NODELIST 'result'
            DocumentBuilder docb = null;
            DocumentBuilderFactory docf = DocumentBuilderFactory.newInstance();
            docb = docf.newDocumentBuilder();

            InputSource is = new InputSource(new ByteArrayInputStream(xmlOut.getWriter().toString().getBytes()));

            Document docm = docb.parse(is);

            NodeList pagtosFisica = docm.getElementsByTagName("ns2:AquisicaoProducao_3Response");

            int tamPagtosFisica = pagtosFisica.getLength();

            for (int i = 0; i < tamPagtosFisica; i++) {
                Node resultSenior = pagtosFisica.item(i);

                if (resultSenior.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoResultSenior = (Element) resultSenior;
                    NodeList filhos = elementoResultSenior.getChildNodes();
                    int tamanho = filhos.getLength();

                    for (int j = 0; j < tamanho; j++) {
                        Node noFilho = filhos.item(j);

                        //System.out.println("\nCabec Filho - volta " + j + ", lendo: " + noFilho);
                        if (noFilho.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementoFilho = (Element) noFilho;
                            switch (elementoFilho.getTagName()) {
                                case "result":
                                    System.out.println("Resposta do Servidor: " + elementoFilho.getTextContent());
                                    resposta = true;
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Motivo: " + elementoFilho.getTextContent());
                                    break;
                            }
                        }

                    }
                }
            }
             
            
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível integrar com Folha de Pagamentos automaticamente: " + ex);
        }

        return resposta;
    }

    
    public static void main(String[] args) {
        IntegraFunrural integ = new IntegraFunrural("I", "11/2017", 1, 19, "01510933000", "Luiz Fernando Dill Barcellos", "123455", "2", "26/11/2017", Double.parseDouble("56890"), null, null);
    }
}
