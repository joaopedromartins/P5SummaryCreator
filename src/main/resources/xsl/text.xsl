<?xml version="1.0" encoding="UTF-8"?>
<html xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xsl:version="1.0"> 
    <head><title> metric_data </title></head> 
	<body> 
		<h1> CNN News </h1> 
		<table border="0"> 
			<xsl:for-each select="//noticiasRegiao"> 
				<tr> 
					<td><h1> Regiao: <xsl:value-of select="@regiao"/>  </h1></td>
					
					<table border="1"> 
						<tr> 
							<td><b> titulo  </b></td> 
							<td><b> data   </b></td>
							<td><b> seccao   </b></td> 
							<td><b> url   </b></td> 
							<td><b> autor   </b></td> 
							<td><b> cabecalho   </b></td> 
							<td><b> descricao   </b></td> 
							<td><b> corpo   </b></td> 
							<td><b> imagem   </b></td> 
						</tr> 
						<xsl:for-each select="./noticia"> 
							<tr> 
								<td> <xsl:value-of select="titulo"/>  </td>
								<td> <xsl:value-of select="data"/>  </td>
								<td> <xsl:value-of select="seccao"/>  </td>
								<td> <xsl:value-of select="url"/>  </td>
								<td> <xsl:value-of select="autor"/>  </td>
								<td> <xsl:value-of select="cabecalho"/>  </td>
								<td> <xsl:value-of select="descricao"/>  </td>
								<td> <xsl:value-of select="corpo"/>  </td>
								<td> <xsl:value-of select="imagem"/>  </td>
							</tr> 
						</xsl:for-each> 
					</table> 
				</tr> 
			</xsl:for-each> 
		</table> 
	</body> 
</html> 		
