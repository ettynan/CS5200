<?xml version="1.0" encoding="UTF-8"?> 
<!--
Root element of a stylesheet: Define namespaces, such as xls. Define version (2.0 is current). Optionally exclude namespaces.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">
<!--
Output format, for example xml, html, or text. -->
<xsl:output method="html" indent="yes"></xsl:output> 
<!--
    Template to apply when "match" pattern is encountered.
    The "match" pattern is an XPath expression.
    A single stylesheet can contain multiple templates.
    Within a template, you can apply the other templates to the
    current element (and its child nodes) with:
    <xsl:apply-templates select="sub-template-pattern"></xsl:apply-templates>.
-->
<xsl:template match="/ReviewApplication">
<!--
    Within a template, you can use a mix of HTML and XSL. The "select" patterns are XPath expressions.
    --> 
    <html>
        <head>
            <title>UserName to Reviews count</title>
        </head>
        <body>
            <table border="1">
                <tr>
                    <th>UserName</th>
                    <th>Reviews</th>
                </tr>
                    <xsl:for-each select="/ReviewApplication/Users/User">
                        <xsl:variable name = "username" select = "UserName"></xsl:variable>
                        <xsl:variable name= "reviewcount" select= "/ReviewApplication/Reviews[count(Review)]/Review[UserName/text()=$username]"></xsl:variable>
                        <tr>
                            <td>
                                <xsl:value-of select="$username/text()"></xsl:value-of>
                            </td>
                            <td>
                                <xsl:value-of select="$reviewcount/text()"></xsl:value-of>
                            </td> 
                        </tr>
                    </xsl:for-each> 
                </table>
            </body>
        </html> 
    </xsl:template>
</xsl:stylesheet>