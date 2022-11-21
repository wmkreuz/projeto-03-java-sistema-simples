package br.com.empresa.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
  
public class ZipUtil {
	
  public List<ZipEntry> listarEntradasZip( File arquivo ) throws ZipException, IOException {  
    List<ZipEntry> entradasDoZip = new ArrayList<ZipEntry>();  
    ZipFile zip = null;  
    try {  
      zip = new ZipFile( arquivo );  
      Enumeration<?> e = zip.entries();
      ZipEntry entrada = null;  
      while( e.hasMoreElements() ) {  
        entrada = (ZipEntry) e.nextElement();  
        entradasDoZip.add ( entrada );  
      }  
      setArquivoZipAtual( arquivo );  
    }  
    finally {  
      if( zip != null ) {  
        zip.close();  
      }  
    }  
    return entradasDoZip;  
  }  
  
  public void extrairZip( File diretorio ) throws ZipException, IOException {  
    extrairZip( this.getArquivoZipAtual(), diretorio );  
  }  
  
  public void extrairZip( File arquivoZip, File diretorio ) throws ZipException, IOException {  
    ZipFile zip = null;  
    File arquivo = null;  
    InputStream is = null;  
    OutputStream os = null;  
    byte[] buffer = new byte[TAMANHO_BUFFER];  
    try {  
      //cria diret�rio informado, caso n�o exista  
      if( !diretorio.exists() ) {  
        diretorio.mkdirs();  
      }  
      if( !diretorio.exists() || !diretorio.isDirectory() ) {  
        throw new IOException("Informe um diret�rio v�lido");  
      }  
      zip = new ZipFile( arquivoZip );  
      Enumeration<?> e = zip.entries();  
      while( e.hasMoreElements() ) {  
        ZipEntry entrada = (ZipEntry) e.nextElement();  
        arquivo = new File( diretorio, entrada.getName() );  
        //se for diret�rio inexistente, cria a estrutura   
        //e pula pra pr�xima entrada  
        if( entrada.isDirectory() && !arquivo.exists() ) {  
          arquivo.mkdirs();  
          continue;  
        }  
        //se a estrutura de diret�rios n�o existe, cria  
        if( !arquivo.getParentFile().exists() ) {  
          arquivo.getParentFile().mkdirs();  
        }  
        try {  
          //l� o arquivo do zip e grava em disco  
          is = zip.getInputStream( entrada );  
          os = new FileOutputStream( arquivo );  
          int bytesLidos = 0;  
          if( is == null ) {  
            throw new ZipException("Erro ao ler a entrada do zip: "+entrada.getName());  
          }  
          while( (bytesLidos = is.read( buffer )) > 0 ) {  
            os.write( buffer, 0, bytesLidos );  
          }  
        } finally {  
          if( is != null ) {  
            try {  
              is.close();  
            } catch( Exception ex ) {}  
          }  
          if( os != null ) {  
            try {  
              os.close();  
            } catch( Exception ex ) {}  
          }  
        }  
      }  
    } finally {  
      if( zip != null ) {  
        try {  
          zip.close();  
        } catch( Exception e ) {}  
      }  
    }  
  }  
    
  @SuppressWarnings("rawtypes")
public List criarZip( File arquivoZip, File[] arquivos ) throws ZipException, IOException {  
    FileOutputStream fos = null;  
    BufferedOutputStream bos = null;  
    setArquivoZipAtual( null );  
    try {  
      //adiciona a extens�o .zip no arquivo, caso n�o exista  
      if( !arquivoZip.getName().toLowerCase().endsWith(".zip") ) {  
        arquivoZip = new File( arquivoZip.getAbsolutePath()+".zip" );  
      }  
      fos = new FileOutputStream( arquivoZip );  
      bos = new BufferedOutputStream( fos, TAMANHO_BUFFER );  
      List listaEntradasZip = criarZip( bos, arquivos );  
      setArquivoZipAtual( arquivoZip );  
      return listaEntradasZip;  
    }  
    finally {  
      if( bos != null ) {  
        try {  
          bos.close();  
        } catch( Exception e ) {}  
      }  
      if( fos != null ) {  
        try {  
          fos.close();  
        } catch( Exception e ) {}  
      }  
    }  
  }  
    
  @SuppressWarnings({ "rawtypes", "unchecked" })
public List criarZip( OutputStream os, File[] arquivos ) throws ZipException, IOException {  
    if( arquivos == null || arquivos.length < 1 ) {  
      throw new ZipException("Adicione ao menos um arquivo ou diret�rio");  
    }  
    List listaEntradasZip = new ArrayList();  
    ZipOutputStream zos = null;  
    try {  
      zos = new ZipOutputStream( os );  
      for( int i=0; i<arquivos.length; i++ ) {  
        String caminhoInicial = arquivos[i].getParent();  
        List novasEntradas = adicionarArquivoNoZip( zos, arquivos[i], caminhoInicial );  
        if( novasEntradas != null ) {  
          listaEntradasZip.addAll( novasEntradas );  
        }  
      }  
    }  
    finally {  
      if( zos != null ) {  
        try {  
          zos.close();  
        } catch( Exception e ) {}  
      }  
    }  
    return listaEntradasZip;  
  }  
    
  @SuppressWarnings({ "rawtypes", "unchecked" })
private List adicionarArquivoNoZip( ZipOutputStream zos, File arquivo, String caminhoInicial ) throws IOException {  
    List listaEntradasZip = new ArrayList();  
    FileInputStream fis = null;  
    BufferedInputStream bis = null;  
    byte buffer[] = new byte[TAMANHO_BUFFER];  
    try {  
      //diret�rios n�o s�o adicionados  
      if( arquivo.isDirectory() ) {  
        //recursivamente adiciona os arquivos dos diret�rios abaixo  
        File[] arquivos = arquivo.listFiles();  
        for( int i=0; i<arquivos.length; i++ ) {  
          List novasEntradas = adicionarArquivoNoZip( zos, arquivos[i], caminhoInicial );  
          if( novasEntradas != null ) {  
            listaEntradasZip.addAll( novasEntradas );  
          }  
        }  
        return listaEntradasZip;  
      }  
      String caminhoEntradaZip = null;  
      int idx = arquivo.getAbsolutePath().indexOf(caminhoInicial);  
      if( idx >= 0 ) {  
        //calcula os diret�rios a partir do diret�rio inicial  
        //isso serve para n�o colocar uma entrada com o caminho completo  
        caminhoEntradaZip = arquivo.getAbsolutePath().substring( idx+caminhoInicial.length()+1 );  
      }  
      ZipEntry entrada = new ZipEntry( caminhoEntradaZip );  
      zos.putNextEntry( entrada );  
      zos.setMethod( ZipOutputStream.DEFLATED );  
      fis = new FileInputStream( arquivo );  
      bis = new BufferedInputStream( fis, TAMANHO_BUFFER );  
      int bytesLidos = 0;  
      while((bytesLidos = bis.read(buffer, 0, TAMANHO_BUFFER)) != -1) {  
        zos.write( buffer, 0, bytesLidos );  
      }  
      listaEntradasZip.add( entrada );  
    }  
    finally {  
      if( bis != null ) {  
        try {  
          bis.close();  
        } catch( Exception e ) {}  
      }  
      if( fis != null ) {  
        try {  
          fis.close();  
        } catch( Exception e ) {}  
      }  
    }  
    return listaEntradasZip;  
  }  
    
  public void fecharZip() {  
    setArquivoZipAtual( null );  
  }  
    
  public File getArquivoZipAtual() {  
    return arquivoZipAtual;  
  }  
    
  private void setArquivoZipAtual(File arquivoZipAtual) {  
    this.arquivoZipAtual = arquivoZipAtual;  
  }  
    
  private File arquivoZipAtual;  
  private static final int TAMANHO_BUFFER = 2048; // 2 Kb  
} 
