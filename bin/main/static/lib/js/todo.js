function initTodo(){
    var Items = {
        base_url: 'http://localhost:8080',
        continer: {},
        standardItem: {
            name: {value: "", label:"Nume", type:"text"},
            description: {value: "", label: "Descriere", type: "text"},
            startDate: {value: "", label: "Start Date", type:"date"},
            endDate: {value: "", label: "End Date", type:"date"},
            done: {value: false, label: "Done", type:"checkbox", hide:true},
            id:{value: null, label:"ID", type:"text", hide:true}
        },
        itemTpl: "",
        listItems: [],
        cWindow:null,
        init: function(){
            var self = this
            this.continer = $("#body")
            this.itemTpl = this.getTplItem()
            this.getWeekItems()
            this.cWindow = this.getWeekItems
            this.navigator()
        },
        requestData: function(url, data){
            url = this.base_url + '/' + url
            return $.ajax(url, {
                type: 'POST',
                dataType: 'JSON',
                data: data
            })
        },
        CheckDone: function(elem, data){
            this.requestData('update', data).then(function(){
                if(data.done.value)
                    elem.addClass('verde')
                else
                    elem.removeClass('verde')
            })
        },
        getWeekItems: function(){
            var self = this
            var param = {} 
            this.requestData('thisWeek', param).then(function(res){
                self.listItems = self.process(res)
                self.rander()
            })
        },
        getAllItems: function(){
            var self = this
            var param = {} 
            this.requestData('all', param).then(function(res){
                self.listItems = self.process(res)
                self.rander()
            })
        },
        process: function(res){
            var self = this,
            model = this.standardItem,
            lista = [];
            $.each(res, function(key, item){ 
                var elem = model
                $.each(item, function(keyModel, valueModel){
                    elem[keyModel].value = valueModel
                })
                lista.push(elem)
            })
            return lista
        },
        
        rander: function(){
            return this.boxed()
        },
        getTplItem: function(){
            this.continer.find(".list-group-item:last").get(0).outerHTML
        },
        DateBold: function(date){
            var d = new Date(date)
            var now = new Date()
            return (d.getMonth()==now.getMonth() && d.getDate() == now.getDate())
        },
        processItemTpl: function(data, index){
            var self = this
            var name = $("<span class='nameImportant'>"+data.name.value+"</span>"),
            desc = $("<span>"+data.description.value+"</span>"),
            start = $("<span>"+data.startDate.value+"</span>"),
            end = $("<span>"+data.endDate.value+"</span>"),
            edit = $("<span class='edit'><i class='fa-icon fa-edit'/></span>"),
            done = $("<input type='checkbox'/>");
            done.attr('checked', data.done.value)
            var elem = $(this.itemTpl).find("span, div, input").remove().html("")
            elem.append(name)
            elem.append(desc)
            elem.append(start)
            elem.append(end)
            elem.append(done)

            elem.hover(function(){
                $(this).addClass('active')
            }, function(){
                $(this).removeClass('active')
            })

            elem.click(function(){
                if($(this).hasClass('active'))
                    $(this).removeClass('active')
                else
                    $(this).addClass('active')
            })
            done.change(function(){
                data["done"].value = $(this).is(":checked")
                self.CheckDone(elem, data)
            })
            edit.click(function(){
                self.form(elem, index)
            })
            return elem
        },
        boxed: function(){
            var self = this
            this.continer.find(".list-group-item").remove()
            var listBox = this.continer.find("ul")
            $.each(this.listItems, function(index, item){
                listBox.append(self.processItemTpl(item, index))
            })
        },
        navigator: function(){
            var self = this
            $("#thisWeek").click(function(e){
                e.preventDefault()
                self.getWeekItems()
                self.cWindow = self.getWeekItems
            })
            $("#all").click(function(e){
                e.preventDefault()
                self.getAllItems()
                self.cWindow = self.getAllItems
            })
            $("#addNew").click(function(e){
                e.preventDefault()
                self.form()
                self.cWindow = self.getWeekItems
            })
        },

        form: function(elem, index){
            var self = this
            var data = this.standardItem
            if(elem){
                data = elem
            }
            var form = $('<form>\
                            <fieldset>\
                            <button type="button" class="btn btn-primary">Trimite</button>\
                            </fieldset>\
                          </form>')
            
            $.each(data, function(key, entry){
                var e = $('<div class="form-group row">\
                        <label for="'+key+'" class="col-sm-2 col-form-label">'+entry.label+'</label>\
                        <div class="col-sm-10">\
                            <input type="'+entry.type+'" name="'+key+'" class="form-control" id="'+key+'" value="'+entry.value+'">\
                        </div>\
                    </div>')
                if(entry.hide)
                    e.hide()
                form.prepend(e)
            })
            $(this.continer).find("ul, form").remove()
            $(this.continer).append(form)
            
            form.find("button").click(function(){
                var jsData = form.serialize()
                if(elem)
                    self.edit('update', jsData, elem, index)
                else
                    self.edit('add', jsData, elem, index)
            })
        },
        
        edit: function(typeEdit, data, elem, index){
            var self = this
            this.requestData(typeEdit, data).then(function(res){
                $(self.continer).find("form").remove()
                $(self.continer).append($('<ul class="list-group"/>'))
                if(typeEdit=='add')
                    self.getWeekItems()
                else{
                    self.listItems[index] = self.process(res)
                    self[cWindow]()
                }
            })
        }
    }
    Items.init()
}

$(document).ready(function(){
    new initTodo()
})
