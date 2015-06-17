<?xml version="1.0" encoding="UTF-8"?>
<html xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xsl:version="1.0"> 
    <head>
		<title> Latest CNN News </title>
	
		<style type='text/css'>
			td.justify {text-align:justify;}
			p.justify {text-align:justify;}
			a.hrefclass {text-decoration : none; color:black; }
			h2.h2class {color:darkgrey; }
			body {font-family: Verdana; background-image: url("http://4.bp.blogspot.com/-fu8uUVfMGKs/UObROHEV8RI/AAAAAAAADes/RCz226AZEWw/s1600/texture+background+4.png");}
		</style>
	</head> 
	
	<body>
		
		<br/>
		<h1><center> Latest CNN News </center></h1> 
		<br/>
		
		<xsl:for-each select="//noticiasRegiao"> 
			<hr/>
			<center> <h2 class="h2class"> Region: <xsl:value-of select="@regiao"/>  </h2> </center> 
			
			<xsl:for-each select="./noticia"> 
				<hr/>
				<br/>
				<center> <h3> <b> <a class="hrefclass" href="{url}"> <xsl:value-of select="titulo"/> </a> </b> </h3> </center>
				
				<br/>		
				<table border="0" width="100%"> 
					<tr> 
						<td> 
							<table border="0"> 
								<tr align="left" valign="top">
									<td> <b> Headline:	</b> </td> 
									<td class="justify"> <xsl:value-of select="descricao"/>  </td>
								</tr>
								<tr>
									<td> <b> Published:		</b> </td> 
									<td> <xsl:value-of select="data"/> </td>
								</tr>
								<tr>
									<td> <b> Section:	</b> </td> 
									<td> <xsl:value-of select="seccao"/>  </td>
								</tr>
								<tr>
									<td> <b> Author:		</b> </td> 
									<td> <xsl:value-of select="autor"/>  </td>
								</tr>
							</table> 
						</td>
						
						<td align="right" valign="top"> 
							<img src="{imagem}" style="height:120px"/>
						</td>
					</tr> 
				</table> 
				
				
				<p class="justify"> <xsl:value-of select="corpo"/>  </p>
				
				<br/> 
			</xsl:for-each> 
			
			<br/> 
			
		</xsl:for-each>
			
		<br/>  
		
	</body> 
</html> 		
