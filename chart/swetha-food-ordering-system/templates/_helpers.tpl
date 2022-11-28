{{/* vim: set filetype=mustache: */}}
{{- /*
service.labels.standard prints the standard service Helm labels.
The standard labels are frequently used in metadata.
*/ -}}

{{- define "service.microservice.labels" -}}
app/version: {{ .Chart.Version | quote }}
app/service: {{ .Chart.Name | quote }}
{{- end -}}

{{- define "service.labels.standard" -}}
app/release: {{ .Release.Name | quote }}
{{- end -}}

{{- define "service.match.labels" -}}
app/release: {{ .Release.Name | quote }}
{{- end -}}


{{- define "service.pod.annotations" -}}

{{- end -}}
