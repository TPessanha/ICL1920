/* Generated by: ParserGeneratorCC: Do not edit this line. StringProvider.java Version 1.1 */
/* ParserGeneratorCCOptions:KEEP_LINE_COLUMN=true */
package parser;

  
import java.io.IOException;
  
public class StringProvider implements Provider {
  private String m_sStr;
  private int m_nPos = 0;
  private final int m_nLen;
  
  public StringProvider(final String sStr) {
    m_sStr = sStr;
    m_nLen = sStr.length();
  }
  
  public int read(final char[] aDest, final int nOfs, final int nLen) throws IOException {
    final int nLeft = m_nLen - m_nPos;
    if (nLeft <= 0)
      return -1;
    
    int nCharsRead = aDest.length - nOfs;
    if (nLen < nCharsRead)
      nCharsRead = nLen;
    if (nLeft < nCharsRead)
      nCharsRead = nLeft;
    
    m_sStr.getChars(m_nPos, m_nPos + nCharsRead, aDest, nOfs);
    m_nPos += nCharsRead;
    
    return nCharsRead;
  }

  public void close() {
    m_sStr = null;
  }
}
/* ParserGeneratorCC - OriginalChecksum=17b6246ecee667ccf5b76eb76ff1eb60 (do not edit this line) */
